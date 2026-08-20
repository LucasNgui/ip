package atom.task;

/**
 * A parent class for tasks.
 */
public class Task {
    protected boolean isDone;
    protected final String description;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Marks a task as done.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Marks a task as undone.
     */
    public void unmark() {
        isDone = false;
    }

    @Override
    public String toString() {
        String cross = isDone ? "X" : " ";
        return String.format("[%s] %s", cross, description);
    }
}
