package atom.command;

import atom.exception.AtomInvalidTypeException;
import atom.exception.AtomMismatchedArgumentsException;
import atom.storage.Storage;
import atom.task.Deadline;
import atom.task.TaskList;
import atom.ui.Ui;

/**
 * Represents a deadline command.
 */
public class DeadlineCommand extends Command {
    /**
     * Instantiates a <code>DeadlineCommand</code>.
     *
     * @param args The arguments to the command.
     * @throws AtomMismatchedArgumentsException If the number of arguments given
     *     does not match the expected number.
     */
    public DeadlineCommand(String[] args)
            throws AtomMismatchedArgumentsException, AtomInvalidTypeException {
        super(args, 2);

        checkDeadlineArgs();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Deadline deadline = new Deadline(args[0].strip(),
                args[1].strip());
        tasks.add(deadline);
        storage.writeTask(Storage.TaskName.D, args);
        return "Alright! I've added this task:\n"
                + deadline
                + String.format("\nYou now have %d tasks in the list.", tasks.size());
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getCommandName() {
        return "deadline";
    }

    /**
     * Checks if the arguments to deadline are correct and then reformats it.
     */
    private void checkDeadlineArgs() {
        if (!args[1].startsWith("by ")) {
            throw new AtomInvalidTypeException(getCommandName());
        }
        args[1] = args[1].substring(3).strip();
    }
}
