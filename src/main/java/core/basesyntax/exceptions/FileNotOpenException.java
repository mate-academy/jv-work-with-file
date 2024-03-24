package core.basesyntax.exceptions;

public class FileNotOpenException extends RuntimeException {
    public FileNotOpenException(String message, Throwable cause) {
        super(message, cause);
    }
}
