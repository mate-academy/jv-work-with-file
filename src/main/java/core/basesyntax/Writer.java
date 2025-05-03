package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Writer {
    public void writeDataToFile(String data, String toFileName) {
        File file = new File(toFileName);
        try {
            Files.write(file.toPath(), data.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file: " + toFileName, e);
        }
    }
}
