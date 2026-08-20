package atom.task;

import atom.exception.AtomInvalidDateException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    private final LocalDate deadline;

    /**
     * Instantiates a <code>Deadline</code> task.
     *
     * @param description The description of the task.
     * @param deadline The deadline of the task.
     */
    public Deadline(String description, String deadline) {
        super(description);
        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            throw new AtomInvalidDateException();
        }
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(),
                deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}