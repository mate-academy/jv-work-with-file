package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Reader {
    public List<String> readCsvFileWithData(Path pathFile) {
        List<String> result;
        try {
            result = Files.readAllLines(pathFile);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + pathFile, e);
        }
        return result;
    }
}
