package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {
    public void writeReportToFile(String inputFile, String inputString) {
        File file = new File(inputFile);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.write(inputString);
        } catch (IOException e) {
            throw new RuntimeException("Ooops! Can't write data to file.", e);
        }
    }
}
