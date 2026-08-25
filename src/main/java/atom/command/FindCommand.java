package atom.command;

import atom.exception.AtomMismatchedArgumentsException;
import atom.storage.Storage;
import atom.task.TaskList;
import atom.ui.Ui;

public class FindCommand extends Command {
    public FindCommand(String[] args) throws AtomMismatchedArgumentsException {
        super(args, 1);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.findList(tasks.find(args[0]));
    }

    @Override
    public String getCommandName() {
        return "find";
    }
}