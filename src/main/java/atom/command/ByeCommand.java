package atom.command;

import atom.exception.AtomMismatchedArgumentsException;
import atom.storage.Storage;
import atom.task.TaskList;
import atom.ui.Ui;

public class ByeCommand extends Command {
    public ByeCommand(String[] args) throws AtomMismatchedArgumentsException {
        super(args, 0);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.bye();
    }

    @Override
    public String getCommandName() {
        return "bye";
    }
}
