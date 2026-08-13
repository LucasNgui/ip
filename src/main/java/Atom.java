import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Atom {
    private final static String name = "Atom";
    private final Scanner scanner;
    private final ArrayList<Task> tasks;

    public Atom() {
        greet();
        scanner = new Scanner(System.in);
        tasks = new ArrayList<>();
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

    private void markTask(int number) {
        tasks.get(number - 1).mark();
        printWrappedText("Awesome! Marking this task as done\n"
                + tasks.get(number - 1));
    }

    private void unmarkTask(int number) {
        tasks.get(number - 1).unmark();
        printWrappedText("Ok. Marking this task as undone\n"
                + tasks.get(number - 1));
    }

    private void addToDo(String[] args) {
        ToDo toDo = new ToDo(args[0].strip());
        addTask(toDo);
    }

    private void addDeadline(String[] args) {
        Deadline deadline = new Deadline(args[0].strip(), args[1].strip());
        addTask(deadline);
    }

    private void addEvent(String[] args) {
        Event event = new Event(args[0].strip(), args[1].strip(), args[2].strip());
        addTask(event);
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
    }

    private void checkCommand(String command, int argsNum) {
        switch (command) {
            case "bye":
            case "list":
                if (argsNum != 0) {
                    throw new AtomMismatchedArgumentsException(command, 0, argsNum);
                }
                break;
            case "mark":
            case "unmark":
            case "delete":
            case "todo":
                if (argsNum != 1) {
                    throw new AtomMismatchedArgumentsException(command, 1, argsNum);
                }
                break;
            case "deadline":
                if (argsNum != 2) {
                    throw new AtomMismatchedArgumentsException(command, 2, argsNum);
                }
                break;
            case "event":
                if (argsNum != 3) {
                    throw new AtomMismatchedArgumentsException(command, 3, argsNum);
                }
                break;
            default:
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
            switch (command) {
                case "bye":
                    bye();
                    return;
                case "list":
                    list();
                    break;
                case "mark":
                case "unmark":
                case "delete":
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
                case "todo":
                    addToDo(args);
                    break;
                case "deadline":
                    addDeadline(args);
                    break;
                case "event":
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
