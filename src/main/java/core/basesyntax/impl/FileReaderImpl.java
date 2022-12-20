package core.basesyntax.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileReaderImpl implements core.basesyntax.FileReader {
    @Override
    public List<String> readFromFile(String fromFileName) {
        try {
            return Files.readAllLines(Path.of(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file: " + fromFileName, e);
        }
    }
}
