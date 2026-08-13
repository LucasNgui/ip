public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        String cross = isDone ? "X" : " ";
        return String.format("[T][%s] %s", cross, description);
    }
}
