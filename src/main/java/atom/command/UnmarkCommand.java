package atom.command;

import atom.exception.AtomInvalidTypeException;
import atom.exception.AtomMismatchedArgumentsException;
import atom.storage.Storage;
import atom.task.TaskList;
import atom.ui.Ui;

public class UnmarkCommand extends Command {
    public UnmarkCommand(String[] args)
            throws AtomMismatchedArgumentsException, AtomInvalidTypeException {
        super(args, 1);
        try {
            Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            throw new AtomInvalidTypeException(getCommandName());
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int idx = Integer.parseInt(args[0]);
        tasks.unmark(idx - 1);
        ui.unmark(tasks.get(idx - 1));
        storage.unmarkTask(idx);
    }

    @Override
    public String getCommandName() {
        return "unmark";
    }
}