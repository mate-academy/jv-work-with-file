package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String OPERATION_SUPPLY = "supply";
    private static final String OPERATION_BUY = "buy";
    private static final String OPERATION_RESULT = "result";
    private static final String COMMA = ",";
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public String getStatistic(String fromFileName, String toFileName) {
        String[] transactions = readTransactions(fromFileName);
        int[] totals = calculateTotals(transactions, fromFileName);
        String report = buildReport(totals[0], totals[1]);
        writeReport(toFileName, report);
        return report;
    }

    private String[] readTransactions(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            return reader.lines().toArray(String[]::new);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file: " + fromFileName, e);
        }
    }

    private int[] calculateTotals(String[] transactions, String fromFileName) {
        int supply = 0;
        int buy = 0;
        for (int i = 0; i < transactions.length; i++) {
            String line = transactions[i];
            if (line == null || line.trim().isEmpty()) {
                continue;
            }
            String[] parts = line.split(COMMA);
            if (parts.length != 2) {
                throw new RuntimeException("Invalid CSV format in file " + fromFileName
                        + " at line " + (i + 1) + ": \"" + line + "\"");
            }
            if (OPERATION_SUPPLY.equals(parts[0])) {
                supply += parseAmount(line, parts[1]);
            } else if (OPERATION_BUY.equals(parts[0])) {
                buy += parseAmount(line, parts[1]);
            }
        }
        return new int[]{supply, buy};
    }

    private int parseAmount(String line, String value) {
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid number format in line: \"" + line + "\"", e);
        }
    }

    private String buildReport(int supply, int buy) {
        StringBuilder report = new StringBuilder();
        report.append(OPERATION_SUPPLY).append(COMMA).append(supply).append(LINE_SEPARATOR)
                .append(OPERATION_BUY).append(COMMA).append(buy).append(LINE_SEPARATOR)
                .append(OPERATION_RESULT).append(COMMA).append(supply - buy);
        return report.toString();
    }

    private void writeReport(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file: " + toFileName, e);
        }
    }
}

