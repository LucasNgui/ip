package atom.command;

import atom.exception.AtomMismatchedArgumentsException;
import atom.storage.Storage;
import atom.task.TaskList;
import atom.task.ToDo;
import atom.ui.Ui;

public class ToDoCommand extends Command {
    public ToDoCommand(String[] args) throws AtomMismatchedArgumentsException {
        super(args, 1);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ToDo toDo = new ToDo(args[0].strip());
        tasks.add(toDo);
        ui.add(toDo, tasks.size());
        storage.writeTask(Storage.TaskName.T, args);
    }

    @Override
    public String getCommandName() {
        return "todo";
    }
}