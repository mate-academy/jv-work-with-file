package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_OPERATION = "supply";
    private static final String BUY_OPERATION = "buy";
    private static final String RESULT_HEADER = "result";
    private static final String SEPARATOR = ",";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFile(fromFileName);
        String report = generateReport(data);
        writeToFile(toFileName, report);
    }

    private String readFile(String fromFileName) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            handleException(e, fromFileName);
        }
        return content.toString();
    }

    private String generateReport(String data) {
        int[] totals = {0, 0};
        StringBuilder report = new StringBuilder();

        String[] lines = data.split(System.lineSeparator());
        for (String line : lines) {
            processLine(line, totals);
        }

        report.append(SUPPLY_OPERATION).append(SEPARATOR).append(totals[0])
                .append(System.lineSeparator());
        report.append(BUY_OPERATION).append(SEPARATOR).append(totals[1])
                .append(System.lineSeparator());
        report.append(RESULT_HEADER).append(SEPARATOR)
                .append(totals[0] - totals[1]);

        return report.toString();
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            handleException(e, toFileName);
        }
    }

    private void processLine(String line, int[] totals) {
        String[] values = line.split(SEPARATOR);
        if (values.length == 2) {
            String operationType = values[OPERATION_TYPE_INDEX].trim();
            int amount = Integer.parseInt(values[AMOUNT_INDEX].trim());
            if (SUPPLY_OPERATION.equals(operationType)) {
                totals[0] += amount;
            } else if (BUY_OPERATION.equals(operationType)) {
                totals[1] += amount;
            }
        }
    }

    public void handleException(IOException e, String fileName) {
        throw new RuntimeException("Can't read/write data to/from file " + fileName, e);
    }
}
