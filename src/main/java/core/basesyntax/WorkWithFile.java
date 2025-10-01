package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_WORD = "supply";
    private static final String BUY_WORD = "buy";
    private static final String RESULT_KEY = "result";
    private static final String DELIMITER = ",";
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public String getStatistic(String fromFileName, String toFileName) {
        int[] totals = readAndAggregate(fromFileName);
        String report = buildReportString(totals[0], totals[1]);
        writeReportToFile(toFileName, report);
        return report;
    }

    private int[] readAndAggregate(String fromFileName) {
        int supplySum = 0;
        int buySum = 0;
        int[] result = new int[2];
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) {
                    continue;
                }
                String operation = parseLine(line)[0];
                int value = Integer.parseInt(parseLine(line)[1]);
                applyOperation(operation, value, result);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }
        return result;
    }

    private String[] parseLine(String line) {
        String[] parts = line.split(DELIMITER);
        if (parts.length != 2) {
            throw new RuntimeException("Invalid line format in file");
        }
        parts[0] = parts[0].trim().toLowerCase();
        try {
            parts[1] = String.valueOf(Integer.parseInt(parts[1].trim()));
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid number in file", e);
        }
        return parts;
    }

    private void applyOperation(String operation, int value, int[] result) {
        if (operation.equals(SUPPLY_WORD)) {
            result[0] += value;
        } else if (operation.equals(BUY_WORD)) {
            result[1] += value;
        } else {
            throw new RuntimeException("Unknown operation");
        }
    }

    private String buildReportString(int supplySum, int buySum) {
        int result = supplySum - buySum;
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append(SUPPLY_WORD).append(DELIMITER).append(supplySum).append(LINE_SEPARATOR)
                .append(BUY_WORD).append(DELIMITER).append(buySum).append(LINE_SEPARATOR)
                .append(RESULT_KEY).append(DELIMITER).append(result).append(LINE_SEPARATOR);
        return reportBuilder.toString();
    }

    private void writeReportToFile(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file: " + toFileName, e);
        }
    }
}
