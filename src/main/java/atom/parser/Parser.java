package atom.parser;

import atom.Atom;
import atom.command.*;
import atom.exception.AtomException;
import atom.exception.AtomInvalidCommandException;
import atom.exception.AtomInvalidTypeException;
import atom.exception.AtomMismatchedArgumentsException;

import java.util.Scanner;

/**
 * Handles making sense of user input.
 */
public class Parser {
    private final Scanner scanner;

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
     * Instantiates a <code>Parser</code>.
     */
    public Parser() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads the user input and breaks it down into its command and arguments
     *
     * @return A <code>Command</code> representing the broken down command and its arguments.
     * @throws AtomException If the command or its arguments are invalid.
     */
    public Command readLine() throws AtomException {
        String line = scanner.nextLine().strip();
        String[] split = line.split("\\s+", 2);
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
