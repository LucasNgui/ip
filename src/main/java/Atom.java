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

    private boolean checkMarkTask(String s) {
        // check if the string matches "mark %d" or "unmark %d"
        int number;
        String regex = "\\d+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        if (!matcher.find()) {
            return false;
        }
        number = Integer.parseInt(matcher.group());
        if (number < 0 || number > tasks.size()) {
            return false;
        }

        if (s.equals("mark " + number)) {
            markTask(number);
        }
        else if (s.equals("unmark " + number)) {
            unmarkTask(number);
        } else {
            return false;
        }
        return true;
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

    private boolean checkToDo(String s) {
        return s.startsWith("todo ");
    }

    private void addToDo(String s) {
        String description = s.substring(5).strip();
        ToDo toDo = new ToDo(description);
        addTask(toDo);
    }

    private boolean checkDeadline(String s) {
        return s.startsWith("deadline ") && s.contains("/");
    }

    private void addDeadline(String s) {
        String description = s.substring(9).strip();
        String[] ss = description.split("/", 2);
        Deadline deadline = new Deadline(ss[0].strip(), ss[1].strip());
        addTask(deadline);
    }

    private boolean checkEvent(String s) {
        return s.startsWith("event ")
                && s.contains("/")
                && s.indexOf("/") != s.lastIndexOf("/");
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
        switch (line) {
            case "bye":
                bye();
                break;
            case "list":
                list();
                readLine();
                break;
            default:
                if (checkMarkTask(line)) {
                    readLine();
                    break;
                } else if (checkToDo(line)) {
                    addToDo(line);
                    readLine();
                    break;
                }
                else if (checkDeadline(line)) {
                    addDeadline(line);
                    readLine();
                    break;
                }
                else if (checkEvent(line)) {
                    addEvent(line);
                    readLine();
                    break;
                }
                printWrappedText(line);
                tasks.add(new Task(line));
                readLine();
        }
    }

    public static void main(String[] args) {
        Atom atom = new Atom();
    }
}
