package core.basesyntax;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Reads a CSV file with market operations and writes a summary report.
 *
 * <p>Behavior:
 * - Input CSV lines have format "operation,amount" where operation is "supply" or "buy".
 * - For IO errors the method throws RuntimeException wrapping the original exception.
 * - For malformed records (wrong fields or non-numeric amount) the method throws
 *   RuntimeException describing the invalid record.
 * - If the input file is empty, the output report will contain zeros.
 */
public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String DELIMITER = ",";
    private static final int EXPECTED_PARTS = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> lines = readLines(fromFileName);
        long[] sums = processLines(lines);
        String report = buildReport(sums[0], sums[1]);
        writeReport(toFileName, report);
    }

    private List<String> readLines(String fileName) {
        Path path = Path.of(fileName);
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file: " + fileName, e);
        }
    }

    private long[] processLines(List<String> lines) {
        long supplySum = 0L;
        long buySum = 0L;

        for (String rawLine : lines) {
            if (rawLine == null) {
                continue;
            }

            String line = rawLine.trim();
            if (line.isEmpty()) {
                continue;
            }

            String[] parts = line.split(DELIMITER);
            if (parts.length != EXPECTED_PARTS) {
                throw new RuntimeException(
                        "Invalid record (wrong number of fields): \"" + rawLine + "\"");
            }

            String operation = parts[0].trim();
            String amountStr = parts[1].trim();

            long amount;
            try {
                amount = Long.parseLong(amountStr);
            } catch (NumberFormatException e) {
                throw new RuntimeException("Invalid number in record: \"" + rawLine + "\"", e);
            }

            if (SUPPLY.equals(operation)) {
                supplySum += amount;
            } else if (BUY.equals(operation)) {
                buySum += amount;
            } else {
                throw new RuntimeException("Unknown operation in record: \"" + rawLine + "\"");
            }
        }
        return new long[] {supplySum, buySum};
    }

    private String buildReport(long supplySum, long buySum) {
        long result = supplySum - buySum;
        return SUPPLY + DELIMITER + supplySum + System.lineSeparator()
                + BUY + DELIMITER + buySum + System.lineSeparator()
                + RESULT + DELIMITER + result + System.lineSeparator();
    }

    private void writeReport(String fileName, String content) {
        Path path = Path.of(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(content);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: " + fileName, e);
        }
    }
}
