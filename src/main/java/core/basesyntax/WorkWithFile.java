package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private static final String SUPPLY_OPERATION = "supply";
    private static final String BUY_OPERATION = "buy";
    private static final String RESULT_OPERATION = "result";
    private static final String COMMA_SEPARATOR = ",";
    private static final String NEW_LINE_SEPARATOR = System.lineSeparator();
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String content = readFromFile(fromFileName);
        String report = generateReport(content);
        writeToFile(toFileName, report);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder readContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while (((line = reader.readLine()) != null)) {
                readContent.append(line).append(NEW_LINE_SEPARATOR);
            }

        } catch (IOException e) {
            throw new RuntimeException("Can`t read from file: " + fromFileName, e);
        }

        return readContent.toString();
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data into file: " + toFileName,e);
        }
    }

    private String generateReport(String data) {
        StringBuilder report = new StringBuilder();
        String[] lines = data.split(NEW_LINE_SEPARATOR);
        int supplyTotal = 0;
        int buyTotal = 0;
        for (String entry : lines) {
            String[] parts = entry.split(COMMA_SEPARATOR);
            if (parts.length == 2) {
                String operationType = parts[OPERATION_TYPE_INDEX];
                int amount = Integer.parseInt(parts[AMOUNT_INDEX]);
                if (operationType.equals(SUPPLY_OPERATION)) {
                    supplyTotal += amount;
                } else if (operationType.equals(BUY_OPERATION)) {
                    buyTotal += amount;
                } else {
                    throw new IllegalArgumentException("Invalid operation type: " + operationType);
                }
            }
        }

        report.append(SUPPLY_OPERATION).append(COMMA_SEPARATOR).append(supplyTotal)
                .append(NEW_LINE_SEPARATOR)
                .append(BUY_OPERATION).append(COMMA_SEPARATOR).append(buyTotal)
                .append(NEW_LINE_SEPARATOR)
                .append(RESULT_OPERATION).append(COMMA_SEPARATOR).append(supplyTotal - buyTotal);
        return report.toString();
    }
}
