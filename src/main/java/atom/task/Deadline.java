package atom.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import atom.exception.AtomInvalidDateException;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    private static final String DATE_TIME_FORMAT = "MMM d yyyy, h:mm a";
    private static final DateTimeFormatter INPUT_FORMAT =
            DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm")
                    .withResolverStyle(ResolverStyle.STRICT);

    /** The due date */
    private final LocalDateTime deadline;

    /**
     * Instantiates a <code>Deadline</code> task.
     *
     * @param description The description of the task.
     * @param deadline The deadline of the task.
     */
    public Deadline(String description, String deadline) {
        super(description);
        try {
            this.deadline = parseDateTime(deadline);
        } catch (DateTimeParseException e) {
            throw new AtomInvalidDateException();
        }
    }

    /**
     * Parses a timestamp, retaining support for date-only values.
     *
     * @param dateTime The date and time arguments.
     */
    private static LocalDateTime parseDateTime(String dateTime) {
        try {
            return LocalDateTime.parse(dateTime, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            return LocalDate.parse(dateTime).atTime(LocalTime.MIDNIGHT);
        }
    }

    /** @return The date and time of this deadline. */
    public LocalDateTime getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(),
                deadline.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)));
    }
}
