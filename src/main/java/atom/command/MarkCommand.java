package atom.command;

import atom.exception.AtomInvalidTypeException;
import atom.exception.AtomMismatchedArgumentsException;
import atom.exception.AtomTaskNotFoundException;
import atom.storage.Storage;
import atom.task.TaskList;
import atom.ui.Ui;

/**
 * Represents a mark command.
 */
public class MarkCommand extends Command {
    /**
     * Instantiates a <code>MarkCommand</code>.
     *
     * @param args The arguments to the command.
     * @throws AtomMismatchedArgumentsException If the number of arguments given
     *     does not match the expected number.
     * @throws AtomInvalidTypeException If the argument given is not an integer.
     */
    public MarkCommand(String[] args)
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
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws AtomTaskNotFoundException {
        int idx = Integer.parseInt(args[0]);
        tasks.mark(idx - 1);
        ui.mark(tasks.get(idx - 1));
        storage.markTask(idx);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getCommandName() {
        return "mark";
    }
}
