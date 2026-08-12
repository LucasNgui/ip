public class Atom {
    public static void main(String[] args) {
        String name = "Atom";
        String greeting = String.format("~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + "Hi! I'm %s~☆ ヽ(*・ω・)ﾉ\n"
                + "~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + "Bye bye! (￣▽￣)ノ\n"
                + "~~~~~~~~~~~~~~~~~~~~~~~~~\n",
                name
        );
        System.out.println(greeting);
    }
}
