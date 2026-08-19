package atom.parser;

import atom.Atom;
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
        Parser.Line l = p.readLine();

        assertEquals(Atom.Command.BYE, l.command());
        assertArrayEquals(new String[]{}, l.args());
    }

    @Test
    public void readLine_list_extraSpaces_writtenCorrectly() {
        setInput("   list   \n");
        Parser p = new Parser();
        Parser.Line l = p.readLine();

        assertEquals(Atom.Command.LIST, l.command());
        assertArrayEquals(new String[]{}, l.args());
    }

    @Test
    public void readLine_mark_extraSpaces_writtenCorrectly() {
        setInput("mark      1\n");
        Parser p = new Parser();
        Parser.Line l = p.readLine();

        assertEquals(Atom.Command.MARK, l.command());
        assertArrayEquals(new String[]{"1"}, l.args());
    }

    @Test
    public void readLine_unmark_capitalisedCommand_writtenCorrectly() {
        setInput("uNmaRk 10\n");
        Parser p = new Parser();
        Parser.Line l = p.readLine();

        assertEquals(Atom.Command.UNMARK, l.command());
        assertArrayEquals(new String[]{"10"}, l.args());
    }

    @Test
    public void readLine_delete_writtenCorrectly() {
        setInput("delete 64\n");
        Parser p = new Parser();
        Parser.Line l = p.readLine();

        assertEquals(Atom.Command.DELETE, l.command());
        assertArrayEquals(new String[]{"64"}, l.args());
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
        Parser.Line l = p.readLine();

        assertEquals(Atom.Command.TODO, l.command());
        assertArrayEquals(new String[]{"borrow book"}, l.args());
    }

    @Test
    public void readLine_deadline_writtenCorrectly() {
        setInput("deadline return book /by 2024-10-02\n");
        Parser p = new Parser();
        Parser.Line l = p.readLine();

        assertEquals(Atom.Command.DEADLINE, l.command());
        assertArrayEquals(new String[]{"return book", "2024-10-02"}, l.args());
    }

    @Test
    public void readLine_event_writtenCorrectly() {
        setInput("event meeting /from 2pm /to 4pm\n");
        Parser p = new Parser();
        Parser.Line l = p.readLine();

        assertEquals(Atom.Command.EVENT, l.command());
        assertArrayEquals(new String[]{"meeting", "2pm", "4pm"}, l.args());
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
