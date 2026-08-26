package atom;

import atom.command.ByeCommand;
import atom.command.Command;
import atom.exception.AtomException;
import atom.parser.Parser;
import atom.storage.Storage;
import atom.task.TaskList;
import atom.ui.Ui;

/**
 * A chatbot called Atom.
 * Helps the user keep track of tasks such as
 * todos, deadlines and events.
 */
public class Atom {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    /**
     * Instantiates the Atom chatbot.
     */
    public Atom() {
        storage = new Storage();
        tasks = new TaskList(storage.load());
        ui = new Ui();
        parser = new Parser();
    }

    /**
     * Run the Atom chatbot.
     */
    public void run() {
        ui.greet();
        readLine();
    }

    /**
     * Reads the user input and then carries out the appropriate action.
     */
    private void readLine() {
        atom.command.Command command;
        try {
            command = parser.readLine();
            command.execute(tasks, ui, storage);
        } catch (AtomException e) {
            ui.printError(e);
            readLine();
            return;
        }

        if (command instanceof ByeCommand) {
            return;
        }

        readLine();
    }

    public String getResponse(String input) {
        return "input";
//        try {
//            Command command = parser.readLine();
//            command.execute(tasks, ui, storage);
//        } catch (AtomException e) {
//            ui.printError(e);
//            return "";
//        }
//        return "";
    }

    public static void main(String[] args) {
        new Atom().run();
    }
}
