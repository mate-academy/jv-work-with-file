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
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        validateArgs(fromFileName, toFileName);
        int[] totals = calculateTotals(fromFileName);
        String report = buildReport(totals[0], totals[1]);
        writeToFile(report, toFileName);
    }

    private void validateArgs(String fromFilename, String toFileName) {
        if (fromFilename == null || fromFilename.isBlank()
                || toFileName == null || toFileName.isBlank()) {
            throw new IllegalArgumentException("File names must not be null or empty ");
        }
    }

    private void processLine(String line, int[] totals) {

        if (line == null || line.isBlank()) {
            return;
        }
        String[] parts = line.split(DELIMITER);
        if (parts.length < 2) {
            return;
        }
        String operation = parts[0].trim();
        int amount = parseAmount(parts[1].trim());

        if (operation.equals(SUPPLY)) {
            totals[SUPPLY_INDEX] += amount;
        } else if (operation.equals(BUY)) {
            totals[BUY_INDEX] += amount;
        }
    }

    private int[] calculateTotals(String fileName) {
        int[] totals = new int [] {0, 0};

        Path path = Path.of(fileName);

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {

                processLine(line,totals);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fileName, e);
        }
        return totals;
    }

    private int parseAmount(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private String buildReport(int totalSupply, int totalBuy) {
        StringBuilder sb = new StringBuilder();
        sb.append(SUPPLY).append(DELIMITER).append(totalSupply).append(System.lineSeparator());
        sb.append(BUY).append(DELIMITER).append(totalBuy).append(System.lineSeparator());
        sb.append(RESULT).append(DELIMITER).append(totalSupply - totalBuy);
        return sb.toString();
    }

    private void writeToFile(String content, String fileName) {
        Path out = Path.of(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(out)) {
            writer.write(content);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file" + fileName, e);

        }
    }
}




