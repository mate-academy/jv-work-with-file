package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final int OPERATION_TYPE_INDEX = 0;
    public static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFile(fromFileName);
        String report = createReport(dataFromFile);
        writeReportToFile(report, toFileName);
    }

    private void writeReport(String toFileName, int totalSupply, int totalBuy) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + totalSupply);
            writer.newLine();
            writer.write("buy," + totalBuy);
            writer.newLine();
            writer.write("result," + (totalSupply - totalBuy));
        } catch (IOException e) {
            throw new RuntimeException("Cannot write to file", e);
        }
    }
}
