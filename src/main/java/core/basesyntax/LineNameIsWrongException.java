package core.basesyntax;

public class LineNameIsWrongException extends RuntimeException {
    public LineNameIsWrongException(String message) {
        super(message);
    }
}
