import java.util.Scanner;

public class Atom {
    private final static String name = "Atom";
    private final Scanner scanner;

    public Atom() {
        greet();
        scanner = new Scanner(System.in);
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

    private void readLine() {
        String line = scanner.nextLine();
        if (line.equals("bye")) {
            bye();
            return;
        }
        String echo = "~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + line
                + "\n~~~~~~~~~~~~~~~~~~~~~~~~~\n";
        System.out.println(echo);
        readLine();
    }

    public static void main(String[] args) {
        Atom atom = new Atom();
    }
}
