package core.basesyntax;

public interface Readable {
    String read(String fromFileName);

    void write(String fromFileName, String toFileName);
}
