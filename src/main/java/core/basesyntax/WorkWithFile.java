package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMMA = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) {
        int supplyTotal = readTotalByOperation(fromFileName, SUPPLY);
        int buyTotal = readTotalByOperation(fromFileName, BUY);
        String report = buildReport(supplyTotal, buyTotal);
        writeReport(toFileName, report);
        System.out.println(report);
    }

    private int readTotalByOperation(String fileName, String operation) {
        int total = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(COMMA);
                if (parts.length != 2) {
                    continue;
                }
                String op = parts[0].trim();
                if (!op.equals(operation)) {
                    continue;
                }
                try {
                    int value = Integer.parseInt(parts[1].trim());
                    total += value;
                } catch (NumberFormatException e) {
                    throw new RuntimeException(
                            "Invalid number format in file: " + fileName + ", line: " + line,
                            e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file: " + fileName, e);
        }
        return total;
    }

    private String buildReport(int supply, int buy) {
        StringBuilder builder = new StringBuilder();
        builder.append(SUPPLY).append(COMMA).append(supply).append(LINE_SEPARATOR);
        builder.append(BUY).append(COMMA).append(buy).append(LINE_SEPARATOR);
        builder.append(RESULT).append(COMMA).append(supply - buy);
        return builder.toString();
    }

    private void writeReport(String fileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file: " + fileName, e);
        }
    }
}
