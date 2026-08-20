package atom.ui;

import atom.exception.AtomException;
import atom.task.Task;
import atom.task.TaskList;

/**
 * Handles interactions with the user
 * such as printing and formatting text.
 */
public class Ui {
    public static final String NAME = "Atom";

    /**
     * Wraps text in between horizontal lines and prints it out.
     *
     * @param text The text to be printed.
     */
    public void printWrappedText(String text) {
        String wrapper = "~~~~~~~~~~~~~~~~~~~~~~~~~";
        System.out.println(wrapper);
        System.out.println(text);
        System.out.println(wrapper);
    }

    /**
     * Prints out the greeting.
     */
    public void greet() {
        printWrappedText(String.format("Hi! I'm %s! ('^')/", NAME));
    }

    /**
     * Prints out the goodbye message.
     */
    public void bye() {
        printWrappedText("Bye bye! ('^')/");
    }

    /**
     * Prints out the list of tasks.
     *
     * @param list List of tasks to print out.
     */
    public void list(String list) {
        printWrappedText(list);
    }

    /**
     * Prints out the mark task message.
     *
     * @param task The task to be marked.
     */
    public void mark(Task task) {
        printWrappedText("Awesome! Marking this task as done:\n" + task);
    }

    /**
     * Prints out the unmark task message.
     *
     * @param task The task to be unmarked.
     */
    public void unmark(Task task) {
        printWrappedText("Ok. Marking this task as undone:\n" + task);
    }

    /**
     * Prints out the add task message.
     *
     * @param task The task to be added.
     * @param size The total number of tasks currently.
     */
    public void add(Task task, int size) {
        printWrappedText("Alright! I've added this task:\n"
                + task
                + String.format("\nYou now have %d tasks in the list.", size));
    }

    /**
     * Prints out the delete task message.
     *
     * @param task The task to be deleted.
     * @param size The total number of tasks currently.
     */
    public void remove(Task task, int size) {
        printWrappedText("Alright! I've removed this task:\n"
                + task
                + String.format("\nYou now have %d tasks in the list.", size));
    }

    /**
     * Prints out the find task message.
     *
     * @param tasks The <code>TaskList</code> to be printed.
     */
    public void findList(TaskList tasks) {
        printWrappedText("I've found these matching tasks in your list:\n"
                + tasks.toString());
    }
    
    /**
     * Prints out an error message.
     *
     * @param e The exception.
     */
    public void printError(AtomException e) {
        printWrappedText(e.getMessage());
    }
}
