import java.util.Scanner;

public class Atom {
    private final Scanner scanner;
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

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
        this.scanner = new Scanner(System.in);
        this.storage = new Storage();
        this.tasks = new TaskList(storage.load());
        this.ui = new Ui();
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
        if (!args[1].startsWith("by ")) {
            throw new AtomInvalidTypeException("deadline");
        }
        Deadline deadline = new Deadline(args[0].strip(),
                args[1].substring(3).strip());
        addTask(deadline);
        args[1] = args[1].substring(3);
        storage.writeTask(Storage.TaskName.D, args);
    }

    private void addEvent(String[] args) {
        if (!args[1].startsWith("from ") || !args[2].startsWith("to ")) {
            throw new AtomInvalidTypeException("event");
        }
        Event event = new Event(args[0].strip(),
                args[1].substring(5).strip(),
                args[2].substring(3).strip());
        addTask(event);
        args[1] = args[1].substring(5);
        args[2] = args[2].substring(3);
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

    private void checkCommand(String command, int argsNum) {
        try {
            switch (Command.valueOf(command.toUpperCase())) {
            case Command.BYE:
            case Command.LIST:
                if (argsNum != 0) {
                    throw new AtomMismatchedArgumentsException(command, 0, argsNum);
                }
                break;
            case Command.MARK:
            case Command.UNMARK:
            case Command.DELETE:
            case Command.TODO:
                if (argsNum != 1) {
                    throw new AtomMismatchedArgumentsException(command, 1, argsNum);
                }
                break;
            case Command.DEADLINE:
                if (argsNum != 2) {
                    throw new AtomMismatchedArgumentsException(command, 2, argsNum);
                }
                break;
            case Command.EVENT:
                if (argsNum != 3) {
                    throw new AtomMismatchedArgumentsException(command, 3, argsNum);
                }
                break;
            default:
                throw new AtomInvalidCommandException();
            }
        } catch (IllegalArgumentException e) {
            throw new AtomInvalidCommandException();
        }
    }

    private void readLine() {
        String line = scanner.nextLine().strip();
        String[] split = line.split("\\s+", 2);
        String command = split[0];
        String[] args = new String[0];
        if (split.length > 1) {
            args = split[1].trim().split(" /");
        }

        try {
            checkCommand(command, args.length);
            switch (Command.valueOf(command.toUpperCase())) {
            case Command.BYE:
                ui.bye();
                return;
            case Command.LIST:
                ui.list(tasks.toString());
                break;
            case Command.MARK:
            case Command.UNMARK:
            case Command.DELETE:
                int idx;
                try {
                    idx = Integer.parseInt(args[0]);
                } catch (NumberFormatException e) {
                    throw new AtomInvalidTypeException(command);
                }
                if (idx < 1 || idx > tasks.size()) {
                    throw new AtomTaskNotFoundException(idx);
                }
                if (command.equals("mark")) {
                    markTask(idx);
                } else if (command.equals("unmark")) {
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
        Atom atom = new Atom();
    }
}
