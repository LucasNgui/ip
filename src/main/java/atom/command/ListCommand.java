package atom.command;

import atom.exception.AtomMismatchedArgumentsException;
import atom.storage.Storage;
import atom.task.TaskList;
import atom.ui.Ui;

public class ListCommand extends Command {
    public ListCommand(String[] args) throws AtomMismatchedArgumentsException {
        super(args, 0);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.list(tasks.toString());
    }

    @Override
    public String getCommandName() {
        return "list";
    }
}
