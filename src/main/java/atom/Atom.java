package atom;

import atom.exception.AtomException;
import atom.exception.AtomTaskNotFoundException;
import atom.parser.Parser;
import atom.storage.Storage;
import atom.task.*;
import atom.ui.Ui;

public class Atom {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    public enum Command {
        BYE,
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE
    }

    public Atom() {
        storage = new Storage();
        tasks = new TaskList(storage.load());
        ui = new Ui();
        parser = new Parser();
    }

    public void run() {
        ui.greet();
        readLine();
    }

    private void markTask(int idx) {
        tasks.mark(idx - 1);
        ui.mark(tasks.get(idx - 1));
        storage.markTask(idx);
    }

    private void unmarkTask(int idx) {
        tasks.unmark(idx - 1);
        ui.unmark(tasks.get(idx - 1));
        storage.unmarkTask(idx);
    }

    private void addToDo(String[] args) {
        ToDo toDo = new ToDo(args[0].strip());
        addTask(toDo);
        storage.writeTask(Storage.TaskName.T, args);
    }

    private void addDeadline(String[] args) {
        Deadline deadline = new Deadline(args[0].strip(),
                args[1].strip());
        addTask(deadline);
        storage.writeTask(Storage.TaskName.D, args);
    }

    private void addEvent(String[] args) {
        Event event = new Event(args[0].strip(),
                args[1].strip(),
                args[2].strip());
        addTask(event);
        storage.writeTask(Storage.TaskName.E, args);
    }

    private void addTask(Task task) {
        tasks.add(task);
        ui.add(task, tasks.size());
    }

    private void deleteTask(int idx) {
        Task t = tasks.remove(idx - 1);
        ui.remove(t, tasks.size());
        storage.deleteTask(idx);
    }

    private void readLine() {
        Parser.Line line;
        try {
            line = parser.readLine();
        } catch (AtomException e) {
            ui.printError(e);
            readLine();
            return;
        }

        Command command = line.command();
        String[] args = line.args();

        try {
            switch (command) {
            case Command.BYE:
                ui.bye();
                return;
            case Command.LIST:
                ui.list(tasks.toString());
                break;
            case Command.MARK:
            case Command.UNMARK:
            case Command.DELETE:
                int idx = Integer.parseInt(args[0]);
                if (idx < 1 || idx > tasks.size()) {
                    throw new AtomTaskNotFoundException(idx);
                }
                if (command.equals(Command.MARK)) {
                    markTask(idx);
                } else if (command.equals(Command.UNMARK)) {
                    unmarkTask(idx);
                } else {
                    deleteTask(idx);
                }
                break;
            case Command.TODO:
                addToDo(args);
                break;
            case Command.DEADLINE:
                addDeadline(args);
                break;
            case Command.EVENT:
                addEvent(args);
                break;
            default:
                break;
            }
            readLine();
        } catch (AtomException e) {
            ui.printError(e);
            readLine();
        }
    }

    public static void main(String[] args) {
        new Atom().run();
    }
}
