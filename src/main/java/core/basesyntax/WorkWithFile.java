package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private static final String[] OPERATION_NAME = {"supply", "buy"};
    private static final String DELIMITER = ",";
    private static final String RESULT_NAME = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder report = createReport(fromFileName);
        writeToFile(report, toFileName);
    }

    private void writeToFile(StringBuilder report, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + toFileName + e);
        }
    }

    private StringBuilder createReport(String fromFileName) {
        StringBuilder report = new StringBuilder();
        int supplyTotal = 0;
        int buyTotal = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            String value;
            line = reader.readLine();
            while (line != null) {
                if (line.contains(OPERATION_NAME[0])) {
                    value = line.split(DELIMITER)[1];
                    supplyTotal += Integer.parseInt(value);
                } else {
                    value = line.split(DELIMITER)[1];
                    buyTotal += Integer.parseInt(value);
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file" + e);
        }
        return report.append(OPERATION_NAME[0]).append(DELIMITER)
                .append(supplyTotal).append(System.lineSeparator())
                .append(OPERATION_NAME[1]).append(DELIMITER)
                .append(buyTotal).append(System.lineSeparator())
                .append(RESULT_NAME).append(DELIMITER)
                .append(supplyTotal - buyTotal);
    }
}
