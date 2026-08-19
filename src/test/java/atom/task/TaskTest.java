package atom.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void mark() {
        Task markedTask = new Task("borrow book");
        markedTask.mark();
        assertEquals("[X] borrow book", markedTask.toString());
    }

    @Test
    public void unmark() {
        Task unmarkedTask = new Task("borrow book");
        unmarkedTask.mark();
        unmarkedTask.unmark();
        assertEquals("[ ] borrow book", unmarkedTask.toString());
    }

    @Test
    public void testStringConversion() {
        assertEquals("[ ] borrow book", new Task("borrow book").toString());
    }
}
