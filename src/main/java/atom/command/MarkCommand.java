package atom.command;

import atom.exception.AtomInvalidTypeException;
import atom.exception.AtomMismatchedArgumentsException;
import atom.exception.AtomTaskNotFoundException;
import atom.storage.Storage;
import atom.task.TaskList;
import atom.ui.Ui;

public class MarkCommand extends Command {
    public MarkCommand(String[] args)
            throws AtomMismatchedArgumentsException, AtomInvalidTypeException {
        super(args, 1);
        try {
            Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            throw new AtomInvalidTypeException(getCommandName());
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws AtomTaskNotFoundException {
        int idx = Integer.parseInt(args[0]);
        tasks.mark(idx - 1);
        ui.mark(tasks.get(idx - 1));
        storage.markTask(idx);
    }

    @Override
    public String getCommandName() {
        return "mark";
    }
}
