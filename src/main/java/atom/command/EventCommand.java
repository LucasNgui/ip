package atom.command;

import atom.exception.AtomInvalidTypeException;
import atom.exception.AtomMismatchedArgumentsException;
import atom.storage.Storage;
import atom.task.Deadline;
import atom.task.Event;
import atom.task.TaskList;
import atom.ui.Ui;

public class EventCommand extends Command {
    public EventCommand(String[] args)
            throws AtomMismatchedArgumentsException, AtomInvalidTypeException {
        super(args, 3);

        checkEventArgs();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Event event = new Event(args[0].strip(),
                args[1].strip(),
                args[2].strip());
        tasks.add(event);
        ui.add(event, tasks.size());
        storage.writeTask(Storage.TaskName.E, args);
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