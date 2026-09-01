package atom.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void mark() {
        Deadline markedDeadline = new Deadline("return book", "2020-10-10");
        markedDeadline.mark();
        assertEquals("[D][X] return book (by: Oct 10 2020)", markedDeadline.toString());
    }

    @Test
    public void unmark() {
        Deadline unmarkedDeadline = new Deadline("return book", "2020-10-10");
        unmarkedDeadline.mark();
        unmarkedDeadline.unmark();
        assertEquals("[D][ ] return book (by: Oct 10 2020)", unmarkedDeadline.toString());
    }

    @Test
    public void testStringConversion() {
        assertEquals("[D][ ] return book (by: Oct 10 2020)",
                new Deadline("return book", "2020-10-10").toString());
    }
}
