package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";
    private static final String COMMA_REGEX = ",\\s*";
    private static final int INDEX_TYPE = 0;
    private static final int INDEX_AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> lines = readFromFile(fromFileName);
        String report = createReport(lines);
        writeToFile(toFileName, report);
    }

    private List<String> readFromFile(String fileName) {
        try {
            return Files.readAllLines(Paths.get(fileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file: " + fileName, e);
        }
    }

    private String createReport(List<String> lines) {
        int supplyTotal = 0;
        int buyTotal = 0;

        for (String line : lines) {
            if (line.trim().isEmpty()) {
                continue;
            }

            String[] parts = line.split(COMMA_REGEX);
            String operationType = parts[INDEX_TYPE];
            int amount = Integer.parseInt(parts[INDEX_AMOUNT].trim());

            if (operationType.equals(SUPPLY)) {
                supplyTotal += amount;
            } else if (operationType.equals(BUY)) {
                buyTotal += amount;
            }
        }

        int result = supplyTotal - buyTotal;

        return new StringBuilder()
                .append(SUPPLY).append(COMMA).append(supplyTotal).append(System.lineSeparator())
                .append(BUY).append(COMMA).append(buyTotal).append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(result)
                .toString();
    }

    private void writeToFile(String fileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: " + fileName, e);
        }
    }
}
