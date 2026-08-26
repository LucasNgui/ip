package atom.command;

import atom.exception.AtomMismatchedArgumentsException;
import atom.storage.Storage;
import atom.task.TaskList;
import javafx.application.Platform;

/**
 * Represents a bye command.
 */
public class ByeCommand extends Command {
    /**
     * Instantiates a <code>ByeCommand</code>.
     *
     * @param args The arguments to the command.
     * @throws AtomMismatchedArgumentsException If the number of arguments given
     *      does not match the expected number.
     */
    public ByeCommand(String ... args) throws AtomMismatchedArgumentsException {
        super(args, 0);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        Platform.exit();
        return "Bye bye! ('^')/";
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getCommandName() {
        return "bye";
    }
}
