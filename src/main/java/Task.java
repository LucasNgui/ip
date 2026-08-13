public class Task {
    protected boolean isDone;
    protected final String description;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String cross = isDone ? "X" : " ";
        return String.format("[%s] %s", cross, description);
    }
}
