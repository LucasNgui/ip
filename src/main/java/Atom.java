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

    private void greet() {
        String greeting = String.format("~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + "Hi! I'm %s~☆ ヽ(*・ω・)ﾉ\n"
                + "~~~~~~~~~~~~~~~~~~~~~~~~~\n",
                name
        );
        System.out.println(greeting);
    }

    private void bye() {
        String bye = "~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + "Bye bye! (￣▽￣)ノ\n"
                + "~~~~~~~~~~~~~~~~~~~~~~~~~\n";
        System.out.println(bye);
    }

    private void list() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");
        int i = 1;
        for (Task s : tasks) {
            System.out.println(i + ". " + s);
            i++;
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~\n");
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

        readLine();
        return true;
    }

    private void markTask(int number) {
        tasks.get(number - 1).mark();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Awesome! Marking this task as done");
        System.out.println(tasks.get(number - 1));
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    }

    private void unmarkTask(int number) {
        tasks.get(number - 1).unmark();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Ok. Marking this task as undone");
        System.out.println(tasks.get(number - 1));
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    }

    private void readLine() {
        String line = scanner.nextLine();
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
                    break;
                }
                String echo = "~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                        + line
                        + "\n~~~~~~~~~~~~~~~~~~~~~~~~~\n";
                System.out.println(echo);
                tasks.add(new Task(line));
                readLine();
        }
    }

    public static void main(String[] args) {
        Atom atom = new Atom();
    }
}
