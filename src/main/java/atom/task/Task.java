package atom.task;

public class Task {
    protected boolean isDone;
    protected final String description;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    @Override
    public String toString() {
        String cross = isDone ? "X" : " ";
        return String.format("[%s] %s", cross, description);
    }
}
