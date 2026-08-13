public class AtomTaskNotFoundException extends AtomException {
    public AtomTaskNotFoundException(int idx) {
        super(String.format("Oh no! Task %d does not exist.", idx));
    }
}
