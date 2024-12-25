package CustomerException;

public class weakPasswordException extends RuntimeException {
    public weakPasswordException(String message) {
        super(message);
    }
}
