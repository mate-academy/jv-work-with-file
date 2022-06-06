package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class FileReader {

    public List<String> readFile(String fromFileName) {
        File fromFile = new File(fromFileName);
        try {
            return Files.readAllLines(fromFile.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }
    }
}
