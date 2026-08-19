package atom.exception;

public class AtomMismatchedArgumentsException  extends AtomException {
    public AtomMismatchedArgumentsException(String command, int expected, int found) {
        super(String.format("Oh no! %s expects %d arguments but got %d.",
                command, expected, found));
    }
}
