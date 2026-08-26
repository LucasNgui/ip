package atom;

import atom.command.Command;
import atom.exception.AtomException;
import atom.parser.Parser;
import atom.storage.Storage;
import atom.task.TaskList;

/**
 * A chatbot called Atom.
 * Helps the user keep track of tasks such as
 * todos, deadlines and events.
 */
public class Atom {
    private final Storage storage;
    private final TaskList tasks;
    private final Parser parser;

    /**
     * Instantiates the Atom chatbot.
     */
    public Atom() {
        storage = new Storage();
        tasks = new TaskList(storage.load());
        parser = new Parser();
    }

    public String getResponse(String input) {
        String outputMessage;
        try {
            Command command = parser.readLine(input);
            outputMessage = command.execute(tasks, storage);
        } catch (AtomException e) {
            outputMessage = e.getMessage();
        }
        return outputMessage;
    }
}
