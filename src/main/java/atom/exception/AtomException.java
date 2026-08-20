package atom.exception;

/**
 * A parent class for Atom exceptions
 */
public class AtomException extends RuntimeException {
    public AtomException(String message) {
        super(message);
    }
}
