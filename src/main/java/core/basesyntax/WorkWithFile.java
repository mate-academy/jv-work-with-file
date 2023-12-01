package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String supply_operation = "supply";
    private static final String buy_operation = "buy";
    private static final String result_header = "result";
    private static final String comma = ",";
    private static final int operation_type_ = 0;
    private static final int amount_index = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        try {
            String data = readFile(fromFileName);
            String report = generateReport(data);
            writeToFile(toFileName, report);
        } catch (IOException e) {
            handleException(e, fromFileName);
        }
    }

    private String readFile(String fromFileName) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
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

        report.append(supply_operation).append(comma).append(totals[0])
                .append(System.lineSeparator());
        report.append(buy_operation).append(comma).append(totals[1])
                .append(System.lineSeparator());
        report.append(result_header).append(comma)
                .append(totals[0] - totals[1]);

        return report.toString();
    }

    private void writeToFile(String toFileName, String report) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        }
    }

    private void processLine(String line, int[] totals) {
        String[] values = line.split(comma);
        if (values.length == 2) {
            String operationType = values[operation_type_].trim();
            int amount = Integer.parseInt(values[amount_index].trim());
            if (supply_operation.equals(operationType)) {
                totals[0] += amount;
            } else if (buy_operation.equals(operationType)) {
                totals[1] += amount;
            }
        }
    }

    public void handleException(IOException e, String fileName) {
        throw new RuntimeException("Can't read/write data to/from file " + fileName, e);
    }
}



