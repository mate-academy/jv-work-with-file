package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String DELIMITER = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readData(fromFileName);
        int[] totals = calculateTotals(data);
        String report = createReport(totals[0], totals[1]);
        writeReport(toFileName, report);
    }

    private String[] readData(String fromFileName) {
        try (BufferedReader reader = Files.newBufferedReader(Path.of(fromFileName))) {
            return reader.lines().toArray(String[]::new);
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file: " + fromFileName, e);
        }
    }

    private int[] calculateTotals(String[] data) {
        int supplyTotal = 0;
        int buyTotal = 0;

        for (String line : data) {
            String[] parts = line.split(DELIMITER);
            if (parts.length != 2) {
                continue;
            }

            String operation = parts[0].trim();
            int amount = Integer.parseInt(parts[1].trim());

            if (operation.equals(SUPPLY)) {
                supplyTotal += amount;
            } else if (operation.equals(BUY)) {
                buyTotal += amount;
            }
        }
        return new int[]{supplyTotal, buyTotal};
    }

    private String createReport(int supplyTotal, int buyTotal) {
        int result = supplyTotal - buyTotal;
        return new StringBuilder()
                .append(SUPPLY).append(DELIMITER).append(supplyTotal).append(System.lineSeparator())
                .append(BUY).append(DELIMITER).append(buyTotal).append(System.lineSeparator())
                .append(RESULT).append(DELIMITER).append(result)
                .toString();
    }

    private void writeReport(String toFileName, String report) {
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file: " + toFileName, e);
        }
    }
}
