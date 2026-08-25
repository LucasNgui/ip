package atom.command;

import atom.exception.AtomInvalidTypeException;
import atom.exception.AtomMismatchedArgumentsException;
import atom.exception.AtomTaskNotFoundException;
import atom.storage.Storage;
import atom.task.Task;
import atom.task.TaskList;
import atom.ui.Ui;

/**
 * Represents a delete command.
 */
public class DeleteCommand extends Command {
    /**
     * Instantiates a <code>DeleteCommand</code>.
     *
     * @param args The arguments to the command.
     * @throws AtomMismatchedArgumentsException If the number of arguments given
     * does not match the expected number.
     * @throws AtomInvalidTypeException If the argument given is not an integer.
     */
    public DeleteCommand(String[] args)
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
        Task t = tasks.remove(idx - 1);
        ui.remove(t, tasks.size());
        storage.deleteTask(idx);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getCommandName() {
        return "delete";
    }
}