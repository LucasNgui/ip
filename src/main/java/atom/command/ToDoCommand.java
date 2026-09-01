package atom.command;

import atom.exception.AtomMismatchedArgumentsException;
import atom.storage.Storage;
import atom.task.TaskList;
import atom.task.ToDo;

/**
 * Represents a todo command.
 */
public class ToDoCommand extends Command {
    /**
     * Instantiates a <code>ToDoCommand</code>.
     *
     * @param args The arguments to the command.
     * @throws AtomMismatchedArgumentsException If the number of arguments given
     *     does not match the expected number.
     */
    public ToDoCommand(String ... args) throws AtomMismatchedArgumentsException {
        super(args, 1);
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        ToDo toDo = new ToDo(args[0].strip());
        tasks.add(toDo);
        storage.writeTask(Storage.TaskName.T, args);
        return "Alright! I've added this task:\n"
                + toDo
                + String.format("\nYou now have %d tasks in the list.", tasks.size());
    }

    @Override
    public String getCommandName() {
        return "todo";
    }
}
