package atom.ui;

import atom.exception.AtomException;
import atom.task.Task;

public class Ui {
    public static final String NAME = "atom";

    public void printWrappedText(String text) {
        String wrapper = "~~~~~~~~~~~~~~~~~~~~~~~~~";
        System.out.println(wrapper);
        System.out.println(text);
        System.out.println(wrapper);
    }

    public void greet() {
        printWrappedText(String.format("Hi! I'm %s~☆ ヽ(*・ω・)ﾉ", NAME));
    }

    public void bye() {
        printWrappedText("Bye bye! (￣▽￣)ノ");
    }
    
    public void list(String list) {
        printWrappedText(list);
    }

    public void mark(Task task) {
        printWrappedText("Awesome! Marking this task as done:\n" + task);
    }
    
    public void unmark(Task task) {
        printWrappedText("Ok. Marking this task as undone:\n" + task);
    }

    public void add(Task task, int size) {
        printWrappedText("Alright! I've added this task:\n"
                + task
                + String.format("\nYou now have %d tasks in the list.", size));
    }

    public void remove(Task task, int size) {
        printWrappedText("Alright! I've removed this task:\n"
                + task
                + String.format("\nYou now have %d tasks in the list.", size));
    }

    public void printError(AtomException e) {
        printWrappedText(e.getMessage());
    }
}
