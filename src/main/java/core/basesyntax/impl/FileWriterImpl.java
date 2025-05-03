package core.basesyntax.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileWriterImpl implements core.basesyntax.FileWriter {
    @Override
    public void writeToFile(String fromFileName, String data) {
        try {
            Files.writeString(Path.of(fromFileName), data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file: " + data, e);
        }
    }
}
