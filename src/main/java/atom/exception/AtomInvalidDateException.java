package atom.exception;

/**
 * An exception for invalid dates.
 */
public class AtomInvalidDateException extends AtomException {
    /**
     * Initializes an <code>AtomInvalidDateException</code>.
     */
    public AtomInvalidDateException() {
        super("Oh no! Invalid date provided.");
    }
}
