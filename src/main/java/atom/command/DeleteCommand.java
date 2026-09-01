package atom.command;

import atom.exception.AtomInvalidTypeException;
import atom.exception.AtomMismatchedArgumentsException;
import atom.exception.AtomTaskNotFoundException;
import atom.storage.Storage;
import atom.task.Task;
import atom.task.TaskList;

/**
 * Represents a delete command.
 */
public class DeleteCommand extends Command {
    /**
     * Instantiates a <code>DeleteCommand</code>.
     *
     * @param args The arguments to the command.
     * @throws AtomMismatchedArgumentsException If the number of arguments given
     *     does not match the expected number.
     * @throws AtomInvalidTypeException If the argument given is not an integer.
     */
    public DeleteCommand(String ... args)
            throws AtomMismatchedArgumentsException, AtomInvalidTypeException {
        super(args, 1);
        try {
            Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            throw new AtomInvalidTypeException(getCommandName());
        }
    }

    @Override
    public String execute(TaskList tasks, Storage storage)
            throws AtomTaskNotFoundException {
        int idx = Integer.parseInt(args[0]);
        Task t = tasks.remove(idx - 1);
        storage.deleteTask(idx);
        return "Alright! I've removed this task:\n"
                + t
                + String.format("\nYou now have %d tasks in the list.", tasks.size());
    }

    @Override
    public String getCommandName() {
        return "delete";
    }
}
