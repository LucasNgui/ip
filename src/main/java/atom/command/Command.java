package atom.command;

import atom.exception.AtomMismatchedArgumentsException;
import atom.storage.Storage;
import atom.task.TaskList;
import atom.ui.Ui;

/**
 * A parent class to represent commands.
 * Contains the arguments to the command.
 */
public abstract class Command {
    protected String[] args;

    /**
     * Instantiates a <code>Command</code>.
     *
     * @param args The arguments to the command.
     * @param expected The expected number of arguments.
     * @throws AtomMismatchedArgumentsException If the number of arguments given
     *     does not match the expected number.
     */
    public Command(String[] args, int expected) throws AtomMismatchedArgumentsException {
        if (args.length != expected) {
            throw new AtomMismatchedArgumentsException(getCommandName(), expected, args.length);
        }
        this.args = args;
    }

    /**
     * Executes the command.
     *
     * @param tasks The current list of tasks.
     * @param ui The ui.
     * @param storage The storage.
     * @return  The output message.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Retrieves the name of the command.
     *
     * @return The command name.
     */
    public abstract String getCommandName();

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Command c = (Command) obj;
        for (int i = 0; i < args.length; i++) {
            if (!args[i].equals(c.args[i])) {
                System.out.print(args[i]);
                System.out.print(c.args[i]);
                return false;
            }
        }
        return true;
    }
}
