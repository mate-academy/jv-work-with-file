package core.basesyntax;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class Csv implements AutoCloseable {
    public static final char DELIMITER = ',';
    private final BufferedReader reader;

    public Csv(Reader reader) {
        this.reader = new BufferedReader(reader);
    }

    public String nextRow() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public void close() throws Exception {
        reader.close();
    }
}
