package atom.parser;

import atom.Atom;
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
     * Represents a command and its arguments.
     *
     * @param command The <code>Atom.Command</code> provided.
     * @param args The arguments provided.
     */
    public record Line(Atom.Command command, String[] args) {}

    /**
     * Instantiates a <code>Parser</code>.
     */
    public Parser() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads the user input and breaks it down into its command and arguments
     *
     * @return A <code>Line</code> representing the broken down command and its arguments.
     * @throws AtomException If the command or its arguments are invalid.
     */
    public Line readLine() throws AtomException {
        String line = scanner.nextLine().strip();
        String[] split = line.split("\\s+", 2);
        Atom.Command command;
        try {
            command = Atom.Command.valueOf(split[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new AtomInvalidCommandException();
        }
        String[] args = new String[0];
        if (split.length > 1) {
            args = split[1].trim().split(" /");
        }

        checkCommand(command, args.length);
        switch (command) {
            case Atom.Command.MARK:
            case Atom.Command.UNMARK:
            case Atom.Command.DELETE:
                try {
                    Integer.parseInt(args[0]);
                } catch (NumberFormatException e) {
                    throw new AtomInvalidTypeException(command.toString().toLowerCase());
                }
                break;
            case Atom.Command.DEADLINE:
                checkDeadlineArgs(args);
                break;
            case Atom.Command.EVENT:
                checkEventArgs(args);
                break;
        }
        return new Line(command, args);
    }

    /**
     * Checks if the number of arguments matches the command.
     *
     * @param command The command provided.
     * @param argsNum The number of arguments provided.
     * @throws AtomException If the number of arguments do not match the command.
     */
    private void checkCommand(Atom.Command command, int argsNum) throws AtomException {
        switch (command) {
            case Atom.Command.BYE:
            case Atom.Command.LIST:
                if (argsNum != 0) {
                    throw new AtomMismatchedArgumentsException(
                            command.toString().toLowerCase(), 0, argsNum);
                }
                break;
            case Atom.Command.MARK:
            case Atom.Command.UNMARK:
            case Atom.Command.DELETE:
            case Atom.Command.TODO:
            case Atom.Command.FIND:
                if (argsNum != 1) {
                    throw new AtomMismatchedArgumentsException(
                            command.toString().toLowerCase(), 1, argsNum);
                }
                break;
            case Atom.Command.DEADLINE:
                if (argsNum != 2) {
                    throw new AtomMismatchedArgumentsException(
                            command.toString().toLowerCase(), 2, argsNum);
                }
                break;
            case Atom.Command.EVENT:
                if (argsNum != 3) {
                    throw new AtomMismatchedArgumentsException(
                            command.toString().toLowerCase(), 3, argsNum);
                }
                break;
            default:
                throw new AtomInvalidCommandException();
        }
    }

    /**
     * Checks if the arguments to deadline are correct and then reformats it.
     *
     * @param args The arguments to the deadline command.
     */
    private void checkDeadlineArgs(String[] args) {
        if (!args[1].startsWith("by ")) {
            throw new AtomInvalidTypeException("deadline");
        }
        args[1] = args[1].substring(3).strip();
    }

    /**
     * Checks if the arguments to event are correct and then reformats it.
     *
     * @param args The arguments to the event command.
     */
    private void checkEventArgs(String[] args) {
        if (!args[1].startsWith("from ") || !args[2].startsWith("to ")) {
            throw new AtomInvalidTypeException("event");
        }
        args[1] = args[1].substring(5).strip();
        args[2] = args[2].substring(3).strip();
    }
}
