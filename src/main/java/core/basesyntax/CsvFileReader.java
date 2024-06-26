package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CsvFileReader {
    public List<String> readCsvFileWithData(Path pathFile) {
        try {
            return Files.readAllLines(pathFile);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + pathFile, e);
        }
    }
}
