package core.basesyntax.exceptions;

public class FileNotCreateException extends RuntimeException {
    public FileNotCreateException(String message, Throwable cause) {
        super(message, cause);
    }
}
