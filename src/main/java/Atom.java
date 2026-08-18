import java.util.ArrayList;
import java.util.Scanner;

public class Atom {
    private final static String name = "Atom";
    private final Scanner scanner;
    private final ArrayList<Task> tasks;
    private final Storage storage = new Storage();

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
        greet();
        scanner = new Scanner(System.in);
        tasks = storage.load();
        readLine();
    }

    private void printWrappedText(String text) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(text);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    private void greet() {
        printWrappedText(String.format("Hi! I'm %s~☆ ヽ(*・ω・)ﾉ", name));
    }

    private void bye() {
        printWrappedText("Bye bye! (￣▽￣)ノ");
    }

    private void list() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Task s : tasks) {
            sb.append(i).append(". ").append(s).append("\n");
            i++;
        }
        // delete the last line break
        if (!sb.isEmpty()) {
            sb.deleteCharAt(sb.length() - 1);
        }
        printWrappedText(sb.toString());
    }

    private void markTask(int idx) {
        tasks.get(idx - 1).mark();
        printWrappedText("Awesome! Marking this task as done\n"
                + tasks.get(idx - 1));
        storage.markTask(idx);
    }

    private void unmarkTask(int idx) {
        tasks.get(idx - 1).unmark();
        printWrappedText("Ok. Marking this task as undone\n"
                + tasks.get(idx - 1));
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
        printWrappedText("Alright! I've added this task:\n"
                + task
                + String.format("\nYou now have %d tasks in the list.", tasks.size()));
    }

    private void deleteTask(int idx) {
        Task t = tasks.remove(idx - 1);
        printWrappedText("Alright! I've removed this task:\n"
                + t
                + String.format("\nYou now have %d tasks in the list.", tasks.size()));
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
                bye();
                return;
            case Command.LIST:
                list();
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
            printWrappedText(e.getMessage());
            readLine();
        }
    }

    public static void main(String[] args) {
        Atom atom = new Atom();
    }
}
