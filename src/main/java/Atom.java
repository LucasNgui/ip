public class Atom {
    private final static String name = "Atom";

    private static void greet() {
        String greeting = String.format("~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + "Hi! I'm %s~☆ ヽ(*・ω・)ﾉ\n"
                + "~~~~~~~~~~~~~~~~~~~~~~~~~\n",
                name
        );
        System.out.println(greeting);
    }

    private static void bye() {
        String bye = "~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + "Bye bye! (￣▽￣)ノ\n"
                + "~~~~~~~~~~~~~~~~~~~~~~~~~\n";
        System.out.println(bye);
    }

    public static void main(String[] args) {
        greet();
    }
}
