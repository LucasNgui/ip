package atom.command;

import atom.exception.AtomInvalidTypeException;
import atom.exception.AtomMismatchedArgumentsException;
import atom.storage.Storage;
import atom.task.Deadline;
import atom.task.TaskList;
import atom.ui.Ui;

public class DeadlineCommand extends Command {
    public DeadlineCommand(String[] args)
            throws AtomMismatchedArgumentsException, AtomInvalidTypeException {
        super(args, 2);

        checkDeadlineArgs();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Deadline deadline = new Deadline(args[0].strip(),
                args[1].strip());
        tasks.add(deadline);
        ui.add(deadline, tasks.size());
        storage.writeTask(Storage.TaskName.D, args);
    }

    @Override
    public String getCommandName() {
        return "deadline";
    }

    /**
     * Checks if the arguments to deadline are correct and then reformats it.
     */
    private void checkDeadlineArgs() {
        if (!args[1].startsWith("by ")) {
            throw new AtomInvalidTypeException(getCommandName());
        }
        args[1] = args[1].substring(3).strip();
    }
}