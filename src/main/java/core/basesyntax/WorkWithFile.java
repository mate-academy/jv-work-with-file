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

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) {
                    continue;
                }
                String[] parts = line.split(",");
                if (parts.length != 2) {
                    throw new RuntimeException("Invalid line format in file");
                }
                String operation = parts[0].trim().toLowerCase();
                int value;
                try {
                    value = Integer.parseInt(parts[1].trim());
                } catch (NumberFormatException e) {
                    throw new RuntimeException("Invalid number in file", e);
                }

                if (operation.equals(SUPPLY_WORD)) {
                    supplySum += value;
                } else if (operation.equals(BUY_WORD)) {
                    buySum += value;
                } else {
                    throw new RuntimeException("Unknown operation");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }
        return new int[]{supplySum, buySum};
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
