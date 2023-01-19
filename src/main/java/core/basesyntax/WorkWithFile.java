package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_LINE_NAME = "supply";
    private static final String BUY_LINE_NAME = "buy";
    private static final String RESULT_LINE_NAME = "result";
    private static final String DELIMITER = ",";
    private int supplyTotal;
    private int buyTotal;

    public void getStatistic(String fromFileName, String toFileName) {
        supplyTotal = 0;
        buyTotal = 0;
        readFile(fromFileName);
        writeToFile(createReport(), toFileName);
    }

    private void readFile(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line = reader.readLine();
            while (line != null) {
                calculateTotal(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file" + fromFileName, e);
        }
    }

    private void calculateTotal(String line) {
        String amount;
        if (line.contains(SUPPLY_LINE_NAME)) {
            amount = line.split(DELIMITER)[1];
            supplyTotal += Integer.parseInt(amount);
        } else {
            amount = line.split(DELIMITER)[1];
            buyTotal += Integer.parseInt(amount);
        }
    }

    private String createReport() {
        StringBuilder report = new StringBuilder();
        return report.append(SUPPLY_LINE_NAME).append(DELIMITER)
                .append(supplyTotal).append(System.lineSeparator())
                .append(BUY_LINE_NAME).append(DELIMITER)
                .append(buyTotal).append(System.lineSeparator())
                .append(RESULT_LINE_NAME).append(DELIMITER)
                .append(supplyTotal - buyTotal).toString();
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file" + toFileName, e);
        }
    }
}
