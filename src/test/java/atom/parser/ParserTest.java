package atom.parser;

import atom.command.*;
import atom.exception.AtomInvalidCommandException;
import atom.exception.AtomInvalidTypeException;
import atom.exception.AtomMismatchedArgumentsException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;


public class ParserTest {
    private final InputStream originalSystemIn = System.in;

    void setInput(String simulatedInput) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(testIn);
    }

    @AfterEach
    void tearDown() {
        System.setIn(originalSystemIn);
    }

    @Test
    public void readLine_bye_writtenCorrectly() {
        setInput("bye\n");
        Parser p = new Parser();
        Command c = p.readLine();

        assertEquals(new ByeCommand(new String[]{}), c);
        //assertArrayEquals(new String[]{}, c.args);
    }

    @Test
    public void readLine_list_extraSpaces_writtenCorrectly() {
        setInput("   list   \n");
        Parser p = new Parser();
        Command c = p.readLine();

        assertEquals(new ListCommand(new String[]{}), c);
    }

    @Test
    public void readLine_mark_extraSpaces_writtenCorrectly() {
        setInput("mark      1\n");
        Parser p = new Parser();
        Command c = p.readLine();

        assertEquals(new MarkCommand(new String[]{"1"}), c);
    }

    @Test
    public void readLine_unmark_capitalisedCommand_writtenCorrectly() {
        setInput("uNmaRk 10\n");
        Parser p = new Parser();
        Command c = p.readLine();

        assertEquals(new UnmarkCommand(new String[]{"10"}), c);
    }

    @Test
    public void readLine_delete_writtenCorrectly() {
        setInput("delete 64\n");
        Parser p = new Parser();
        Command c = p.readLine();

        assertEquals(new DeleteCommand(new String[]{"64"}), c);
    }

    @Test
    public void readLine_delete_invalidType_exceptionThrown() {
        setInput("delete task\n");
        Parser p = new Parser();

        AtomInvalidTypeException exception = assertThrows(
                AtomInvalidTypeException.class,
                p::readLine
        );

        assertEquals("Oh no! Invalid argument type for delete",
                exception.getMessage());
    }

    @Test
    public void readLine_todo_writtenCorrectly() {
        setInput("todo borrow book\n");
        Parser p = new Parser();
        Command c = p.readLine();

        assertEquals(new ToDoCommand(new String[]{"borrow book"}), c);
    }

    @Test
    public void readLine_deadline_writtenCorrectly() {
        setInput("deadline return book /by 2024-10-02\n");
        Parser p = new Parser();
        Command c = p.readLine();

        assertEquals(new DeadlineCommand(new String[]{"return book", "by 2024-10-02"}), c);
    }

    @Test
    public void readLine_event_writtenCorrectly() {
        setInput("event meeting /from 2pm /to 4pm\n");
        Parser p = new Parser();
        Command c = p.readLine();

        assertEquals(new EventCommand(new String[]{"meeting", "from 2pm", "to 4pm"}), c);
    }

    @Test
    public void readLine_event_mismatchedArguments_exceptionThrown() {
        setInput("event meeting /from 2pm\n");
        Parser p = new Parser();

        AtomMismatchedArgumentsException exception = assertThrows(
                AtomMismatchedArgumentsException.class,
                p::readLine
        );

        assertEquals("Oh no! event expects 3 arguments but got 2.",
                exception.getMessage());
    }

    @Test
    public void readLine_unknownCommand_exceptionThrown() {
        setInput("hello adsasd\n");
        Parser p = new Parser();

        AtomInvalidCommandException exception = assertThrows(
                AtomInvalidCommandException.class,
                p::readLine
        );

        assertEquals("Oh no! I don't know what that means.",
                exception.getMessage());
    }

    @Test
    public void readLine_emptyInput_exceptionThrown() {
        setInput("\n");
        Parser p = new Parser();

        AtomInvalidCommandException exception = assertThrows(
                AtomInvalidCommandException.class,
                p::readLine
        );

        assertEquals("Oh no! I don't know what that means.",
                exception.getMessage());
    }
}
