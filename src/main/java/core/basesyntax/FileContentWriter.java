package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileContentWriter {
    public void write(String filePath, String content) {
        if (filePath == null) {
            throw new RuntimeException("File path must be present");
        }

        File file = new File(filePath);

        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create result CSV file: " + filePath, e);
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(content);
        } catch (IOException e) {
            throw new RuntimeException("Can't write result to CSV file: " + filePath, e);
        }
    }
}
