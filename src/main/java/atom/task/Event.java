package atom.task;

import atom.exception.AtomInvalidDateException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a start and end time.
 */
public class Event extends Task {
    private final LocalDate startTime;
    private final LocalDate endTime;

    /**
     * Instantiates an <code>Event</code> task.
     *
     * @param description The description of the task.
     * @param startTime The starting time of the task.
     * @param endTime The ending time of the task.
     */
    public Event(String description, String startTime, String endTime) {
        super(description);
        try {
            this.startTime = LocalDate.parse(startTime);
            this.endTime = LocalDate.parse(endTime);
        } catch (DateTimeParseException e) {
            throw new AtomInvalidDateException();
        }
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(),
                startTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                endTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}