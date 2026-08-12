import java.util.ArrayList;
import java.util.Scanner;;

public class Atom {
    private final static String name = "Atom";
    private final Scanner scanner;
    private final ArrayList<String> tasks;

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
        for (String s : tasks) {
            System.out.println(i + ". " + s);
            i++;
        }
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
                String echo = "~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                        + line
                        + "\n~~~~~~~~~~~~~~~~~~~~~~~~~\n";
                System.out.println(echo);
                tasks.add(line);
                readLine();
        }
    }

    public static void main(String[] args) {
        Atom atom = new Atom();
    }
}
