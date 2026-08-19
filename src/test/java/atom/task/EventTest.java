package atom.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void mark() {
        Event markedEvent = new Event("meeting",  "2020-10-10",  "2020-10-11");
        markedEvent.mark();
        assertEquals("[E][X] meeting (from: Oct 10 2020 to: Oct 11 2020)", markedEvent.toString());
    }

    @Test
    public void unmark() {
        Event unmarkedEvent = new Event("meeting",  "2020-10-10",  "2020-10-11");
        unmarkedEvent.mark();
        unmarkedEvent.unmark();
        assertEquals("[E][ ] meeting (from: Oct 10 2020 to: Oct 11 2020)", unmarkedEvent.toString());
    }

    @Test
    public void testStringConversion() {
        assertEquals("[E][ ] meeting (from: Oct 10 2020 to: Oct 11 2020)",
                new Event("meeting",  "2020-10-10",  "2020-10-11").toString());
    }
}