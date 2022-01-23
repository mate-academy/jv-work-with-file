package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile {
    public void write(String date, String toFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFile, true))) {
            writer.write(date);
        } catch (IOException e) {
            throw new RuntimeException("Cann`t write data to file", e);
        }
    }
}
