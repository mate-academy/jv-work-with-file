package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFile extends ReadFile {
    public void writeFile(String fromFileName, String toFileName) {
        ReadFile read = new ReadFile();
        read.readFile(fromFileName);
        File toFile = new File(toFileName);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {

            bufferedWriter.write("supply," + read.getSupply() + System.lineSeparator());
            bufferedWriter.write("buy," + read.getBuy() + System.lineSeparator());
            bufferedWriter.write("result," + read.getResult() + System.lineSeparator());

        } catch (IOException e) {
            throw new RuntimeException("Error writing to file: " + toFileName, e);
        }
    }
}
