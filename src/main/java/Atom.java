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

    private void addToDo(String s) {
        String description = s.substring(5).strip();
        ToDo toDo = new ToDo(description);
        addTask(toDo);
    }

    private void addDeadline(String s) {
        String description = s.substring(9).strip();
        String[] ss = description.split("/", 2);
        Deadline deadline = new Deadline(ss[0].strip(), ss[1].strip());
        addTask(deadline);
    }

    private void addEvent(String s) {
        String description = s.substring(6).strip();
        String[] ss = description.split("/", 3);
        Event event = new Event(ss[0].strip(), ss[1].strip(), ss[2].strip());
        addTask(event);
    }

    private void addTask(Task task) {
        tasks.add(task);
        printWrappedText("Alright! I've added this task:\n"
                + task
                + String.format("\nYou now have %d tasks in the list.", tasks.size()));
    }

    private void readLine() {
        String line = scanner.nextLine().strip();
        String[] split = line.split("\\s+", 2);
        String command = split[0];
        String[] args = new String[0];
        if (split.length > 1) {
            args = split[1].trim().split(" /");
        }
        switch (command) {
            case "bye":
                if (args.length == 0) {
                    bye();
                    return;
                }
                break;
            case "list":
                if (args.length == 0) {
                    list();
                }
                break;
            case "mark":
                if (args.length == 1) {
                    int number = Integer.parseInt(args[0]);
                    markTask(number);
                }
                break;
            case "unmark":
                if (args.length == 1) {
                    int number = Integer.parseInt(args[0]);
                    unmarkTask(number);
                }
                break;
            case "todo":
                if (args.length == 1) {
                    addToDo(line);
                }
                break;
            case "deadline":
                if (args.length == 2) {
                    addDeadline(line);
                }
                break;
            case "event":
                if (args.length == 3) {
                    addEvent(line);
                }
                break;
            default:
                break;
        }
        readLine();
    }

    public static void main(String[] args) {
        Atom atom = new Atom();
    }
}
