package atom.command;

import atom.exception.AtomMismatchedArgumentsException;
import atom.storage.Storage;
import atom.task.TaskList;

/**
 * Represents a find command.
 */
public class FindCommand extends Command {
    /**
     * Instantiates a <code>FindCommand</code>.
     *
     * @param args The arguments to the command.
     * @throws AtomMismatchedArgumentsException If the number of arguments given
     *     does not match the expected number.
     */
    public FindCommand(String ... args) throws AtomMismatchedArgumentsException {
        super(args, 1);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "I've found these matching tasks in your list:\n"
                + tasks.find(args[0]).toString();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getCommandName() {
        return "find";
    }
}
