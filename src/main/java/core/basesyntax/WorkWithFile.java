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
    private static final int OPERATIONS = 0;
    private static final int AMOUNTS = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        long[] sums = readAndCalculate(fromFileName);
        writeReport(toFileName, sums[0], sums[1]);
    }

    private long[] readAndCalculate(String fromFileName) {
        long supplySum = 0;
        long buySum = 0;

        try (BufferedReader reader = Files.newBufferedReader(Path.of(fromFileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty()) {
                    continue;
                }

                String[] parts = splitLine(line);
                String operation = parts[OPERATIONS].trim();
                long amount = parseAmount(parts[AMOUNTS]);

                if (operation.equals(SUPPLY)) {
                    supplySum += amount;
                } else if (operation.equals(BUY)) {
                    buySum += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fromFileName, e);
        }
        return new long[] {supplySum, buySum};
    }

    private void writeReport(String toFileName, long supplySum, long buySum) {
        long result = supplySum - buySum;

        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(toFileName))) {
            writeLine(writer, SUPPLY, supplySum);
            writeLine(writer, BUY, buySum);
            writeLine(writer, RESULT, result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file " + toFileName, e);
        }
    }

    private void writeLine(BufferedWriter writer, String key, long value) throws IOException {
        writer.write(key + DELIMITER + value);
        writer.newLine();
    }

    private String[] splitLine(String line) {
        String[] parts = line.split(DELIMITER);
        if (parts.length != 2) {
            throw new RuntimeException("Invalid line format: " + line);
        }
        return parts;
    }

    private long parseAmount(String amountPart) {
        try {
            return Long.parseLong(amountPart.trim());
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid amount: " + amountPart, e);
        }
    }
}
