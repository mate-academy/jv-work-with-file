package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

public class FileWriter {
    public void writeToFile(String fileName, String data) {
        File file = new File(fileName);
        try (BufferedWriter bufferedWriter
                = new BufferedWriter(new java.io.FileWriter(file, true))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: " + fileName, e);
        }
    }
}
