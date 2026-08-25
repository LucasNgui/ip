package atom.command;

import atom.exception.AtomInvalidTypeException;
import atom.exception.AtomMismatchedArgumentsException;
import atom.storage.Storage;
import atom.task.TaskList;
import atom.ui.Ui;

/**
 * Represents a find command.
 */
public class FindCommand extends Command {
    /**
     * Instantiates a <code>FindCommand</code>.
     *
     * @param args The arguments to the command.
     * @throws AtomMismatchedArgumentsException If the number of arguments given
     * does not match the expected number.
     */
    public FindCommand(String[] args) throws AtomMismatchedArgumentsException {
        super(args, 1);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.findList(tasks.find(args[0]));
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getCommandName() {
        return "find";
    }
}