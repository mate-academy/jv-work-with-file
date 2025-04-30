package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ReadFile {
    public String readFileContent(File file) {
        StringBuilder contentOfFile = new StringBuilder();
        try {
            Files.readAllLines(file.toPath()).forEach(line
                    -> contentOfFile.append(line).append(System.lineSeparator()));
        } catch (IOException e) {
            throw new RuntimeException("cant read the content of the file" + file,e);
        }
        return contentOfFile.toString();
    }
}
