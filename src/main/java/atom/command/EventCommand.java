package atom.command;

import atom.exception.AtomInvalidTypeException;
import atom.exception.AtomMismatchedArgumentsException;
import atom.storage.Storage;
import atom.task.Event;
import atom.task.TaskList;

/**
 * Represents an event command.
 */
public class EventCommand extends Command {
    /**
     * Instantiates a <code>EventCommand</code>.
     *
     * @param args The arguments to the command.
     * @throws AtomMismatchedArgumentsException If the number of arguments given
     *     does not match the expected number.
     */
    public EventCommand(String ... args)
            throws AtomMismatchedArgumentsException, AtomInvalidTypeException {
        super(args, 3);

        checkEventArgs();
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        Event event = new Event(args[0].strip(),
                args[1].strip(),
                args[2].strip());
        tasks.add(event);
        storage.writeTask(Storage.TaskName.E, args);
        return "Alright! I've added this task:\n"
                + event
                + String.format("\nYou now have %d tasks in the list.", tasks.size());
    }

    @Override
    public String getCommandName() {
        return "event";
    }

    /**
     * Checks if the arguments to event are correct and then reformats it.
     */
    private void checkEventArgs() {
        if (!args[1].startsWith("from ") || !args[2].startsWith("to ")) {
            throw new AtomInvalidTypeException(getCommandName());
        }
        args[1] = args[1].substring(5).strip();
        args[2] = args[2].substring(3).strip();
    }
}
