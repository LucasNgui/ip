import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDate startTime;
    private final LocalDate endTime;

    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = LocalDate.parse(startTime);
        this.endTime = LocalDate.parse(endTime);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(),
                startTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                endTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}