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

        File fileToWrite = new File(filePath);

        try {
            fileToWrite.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create result CSV file: " + filePath, e);
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileToWrite))) {
            bufferedWriter.write(content);
        } catch (IOException e) {
            throw new RuntimeException("Can't write result to CSV file: " + filePath, e);
        }
    }
}
