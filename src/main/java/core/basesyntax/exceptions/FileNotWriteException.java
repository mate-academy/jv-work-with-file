package core.basesyntax.exceptions;

public class FileNotWriteException extends RuntimeException {
    public FileNotWriteException(String message, Throwable cause) {
        super(message, cause);
    }
}
