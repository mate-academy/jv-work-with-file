package core.basesyntax.exceptions;

public class FileNotOpenedException extends RuntimeException {
    public FileNotOpenedException(String message, Throwable cause) {
        super(message, cause);
    }
}
