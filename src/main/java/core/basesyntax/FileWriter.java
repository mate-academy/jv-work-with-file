package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class FileWriter {
    public void writeToFile(String toFileName, String report) {
        File file = new File(toFileName);
        try {
            file.createNewFile();
            Files.writeString(file.toPath(), report, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write file", e);
        }
    }
}
