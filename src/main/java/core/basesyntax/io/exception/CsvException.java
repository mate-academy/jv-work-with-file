package core.basesyntax.io.exception;

public class CsvException extends RuntimeException {
    public CsvException(String message, Exception e) {
        super(message, e);
    }
}
