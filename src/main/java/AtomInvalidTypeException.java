public class AtomInvalidTypeException extends AtomException {
    public AtomInvalidTypeException(String command) {
        super(String.format("Oh no! Invalid argument type for %s", command));
    }
}
