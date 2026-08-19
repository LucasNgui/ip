package atom.exception;

public class AtomTaskNotFoundException extends AtomException {
    public AtomTaskNotFoundException(int idx) {
        super(String.format("Oh no! Atom.Task %d does not exist.", idx));
    }
}
