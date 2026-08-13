public class Event extends Task {
    private final String startTime;
    private final String endTime;

    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        String cross = isDone ? "X" : " ";
        return String.format("[E][%s] %s (from: %s to: %s)",
                cross, description, startTime, endTime);
    }
}