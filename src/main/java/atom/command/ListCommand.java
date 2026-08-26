package atom.command;

import atom.exception.AtomMismatchedArgumentsException;
import atom.storage.Storage;
import atom.task.TaskList;

/**
 * Represents a list command.
 */
public class ListCommand extends Command {
    /**
     * Instantiates a <code>ListCommand</code>.
     *
     * @param args The arguments to the command.
     * @throws AtomMismatchedArgumentsException If the number of arguments given
     *     does not match the expected number.
     */
    public ListCommand(String[] args) throws AtomMismatchedArgumentsException {
        super(args, 0);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.toString();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getCommandName() {
        return "list";
    }
}
