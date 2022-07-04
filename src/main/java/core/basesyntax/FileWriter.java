package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

public class FileWriter {
    public void writeToFile(String fileName, String[] data) {
        File file = new File(fileName);
        for (String dt:data) {
            try (BufferedWriter bufferedWriter
                    = new BufferedWriter(new java.io.FileWriter(file, true))) {
                bufferedWriter.write(dt);
                bufferedWriter.write(System.lineSeparator());
            } catch (IOException e) {
                throw new RuntimeException("Can't write data", e);
            }
        }
    }
}
