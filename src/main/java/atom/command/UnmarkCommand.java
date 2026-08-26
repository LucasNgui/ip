package atom.command;

import atom.exception.AtomInvalidTypeException;
import atom.exception.AtomMismatchedArgumentsException;
import atom.exception.AtomTaskNotFoundException;
import atom.storage.Storage;
import atom.task.TaskList;

/**
 * Represents an unmark command.
 */
public class UnmarkCommand extends Command {
    /**
     * Instantiates an <code>UnmarkCommand</code>.
     *
     * @param args The arguments to the command.
     * @throws AtomMismatchedArgumentsException If the number of arguments given
     *     does not match the expected number.
     * @throws AtomInvalidTypeException If the argument given is not an integer.
     */
    public UnmarkCommand(String[] args)
            throws AtomMismatchedArgumentsException, AtomInvalidTypeException {
        super(args, 1);
        try {
            Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            throw new AtomInvalidTypeException(getCommandName());
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public String execute(TaskList tasks, Storage storage)
            throws AtomTaskNotFoundException {
        int idx = Integer.parseInt(args[0]);
        tasks.unmark(idx - 1);
        storage.unmarkTask(idx);
        return "Ok. Marking this task as undone:\n" + tasks.get(idx - 1);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getCommandName() {
        return "unmark";
    }
}
