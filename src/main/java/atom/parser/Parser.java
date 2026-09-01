package atom.parser;

import atom.command.ByeCommand;
import atom.command.Command;
import atom.command.DeadlineCommand;
import atom.command.DeleteCommand;
import atom.command.EventCommand;
import atom.command.FindCommand;
import atom.command.ListCommand;
import atom.command.MarkCommand;
import atom.command.ToDoCommand;
import atom.command.UnmarkCommand;
import atom.exception.AtomException;
import atom.exception.AtomInvalidCommandException;

/**
 * Handles making sense of user input.
 */
public class Parser {
    /**
     * An enum for the valid commands.
     */
    public enum CommandWord {
        BYE,
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        FIND
    }

    /**
     * Reads the user input and breaks it down into its command and arguments
     *
     * @param input The input string.
     * @return A <code>Command</code> representing the broken down command and its arguments.
     * @throws AtomException If the command or its arguments are invalid.
     */
    public Command readLine(String input) throws AtomException {
        String[] split = input.strip().split("\\s+", 2);
        CommandWord command;
        try {
            command = CommandWord.valueOf(split[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new AtomInvalidCommandException();
        }
        String[] args = new String[0];
        if (split.length > 1) {
            args = split[1].trim().split(" /");
        }

        return switch (command) {
            case CommandWord.BYE -> new ByeCommand(args);
            case CommandWord.LIST -> new ListCommand(args);
            case CommandWord.MARK -> new MarkCommand(args);
            case CommandWord.UNMARK -> new UnmarkCommand(args);
            case CommandWord.DELETE -> new DeleteCommand(args);
            case CommandWord.TODO -> new ToDoCommand(args);
            case CommandWord.FIND -> new FindCommand(args);
            case CommandWord.DEADLINE -> new DeadlineCommand(args);
            case CommandWord.EVENT -> new EventCommand(args);
        };
    }
}
