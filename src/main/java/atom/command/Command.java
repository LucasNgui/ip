package atom.command;

import atom.exception.AtomMismatchedArgumentsException;
import atom.storage.Storage;
import atom.task.TaskList;
import atom.ui.Ui;

public abstract class Command {
    protected String[] args;

    public Command(String[] args, int expected) throws AtomMismatchedArgumentsException {
        if (args.length != expected) {
            throw new AtomMismatchedArgumentsException(getCommandName(), expected, args.length);
        }
        this.args = args;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
    public abstract String getCommandName();
}
