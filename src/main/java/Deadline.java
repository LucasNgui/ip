public class Deadline extends Task {
    private final String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String cross = isDone ? "X" : " ";
        return String.format("[D][%s] %s (by: %s)", cross, description, deadline);
    }
}