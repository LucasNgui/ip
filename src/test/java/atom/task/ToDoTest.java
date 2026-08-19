package atom.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void mark() {
        ToDo markedToDo = new ToDo("borrow book");
        markedToDo.mark();
        assertEquals("[T][X] borrow book", markedToDo.toString());
    }

    @Test
    public void unmark() {
        ToDo unmarkedToDo = new ToDo("borrow book");
        unmarkedToDo.mark();
        unmarkedToDo.unmark();
        assertEquals("[T][ ] borrow book", unmarkedToDo.toString());
    }

    @Test
    public void testStringConversion() {
        assertEquals("[T][ ] borrow book", new ToDo("borrow book").toString());
    }
}
