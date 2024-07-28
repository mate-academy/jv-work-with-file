package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class EntryToFile {
    public void writeToFile(Report report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write("supply," + report.getSupplies() + System.lineSeparator());
            bufferedWriter.write("buy," + report.getBuys() + System.lineSeparator());
            bufferedWriter.write("result," + report.getResult() + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
