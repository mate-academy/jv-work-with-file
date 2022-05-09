package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class FileReader {

    public String[] readFile(String fromFileName) {
        File fromFile = new File(fromFileName);
        try {
            List<String> strings = Files.readAllLines(fromFile.toPath());
            String[] data = new String[strings.size()];
            strings.toArray(data);
            return data;
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }
    }
}
