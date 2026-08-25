package atom.command;

import atom.exception.AtomMismatchedArgumentsException;
import atom.storage.Storage;
import atom.task.TaskList;
import atom.task.ToDo;
import atom.ui.Ui;

/**
 * Represents a todo command.
 */
public class ToDoCommand extends Command {
    /**
     * Instantiates a <code>ToDoCommand</code>.
     *
     * @param args The arguments to the command.
     * @throws AtomMismatchedArgumentsException If the number of arguments given
     * does not match the expected number.
     */
    public ToDoCommand(String[] args) throws AtomMismatchedArgumentsException {
        super(args, 1);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ToDo toDo = new ToDo(args[0].strip());
        tasks.add(toDo);
        ui.add(toDo, tasks.size());
        storage.writeTask(Storage.TaskName.T, args);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getCommandName() {
        return "todo";
    }
}