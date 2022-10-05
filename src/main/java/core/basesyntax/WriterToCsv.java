package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriterToCsv {
    public void writeRecords(String file, String[] records) {
        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter(new File(file), true))) {
            for (String record : records) {
                bufferedWriter.write(record);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write info to file", e);
        }
    }
}
