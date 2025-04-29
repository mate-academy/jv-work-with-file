package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> lines = readFile(fromFileName);
        String report = generateReport(lines);
        writeToFile(toFileName, report);
    }

    private List<String> readFile(String fromFileName) {
        try {
            return Files.readAllLines(Path.of(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file: " + fromFileName, e);
        }
    }

    private String generateReport(List<String> lines) {
        int supplyTotal = 0;
        int buyTotal = 0;

        for (String line : lines) {
            String[] parts = line.split(COMMA);
            String operation = parts[0];
            int amount = Integer.parseInt(parts[1]);

            if (SUPPLY.equals(operation)) {
                supplyTotal += amount;
            } else if (BUY.equals(operation)) {
                buyTotal += amount;
            }
        }

        int resultTotal = supplyTotal - buyTotal;

        return String.join(System.lineSeparator(),
                SUPPLY + COMMA + supplyTotal,
                BUY + COMMA + buyTotal,
                RESULT + COMMA + resultTotal);
    }

    private void writeToFile(String toFileName, String report) {
        try {
            Files.writeString(Path.of(toFileName), report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: " + toFileName, e);
        }

    private List<String> readFile(String fromFileName) {
        try {
            return Files.readAllLines(Path.of(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file: " + fromFileName, e);
        }
    }

    private String generateReport(List<String> lines) {
        int supplyTotal = 0;
        int buyTotal = 0;

        for (String line : lines) {
            String[] parts = line.split(COMMA);
            String operation = parts[0];
            int amount = Integer.parseInt(parts[1]);

            if (SUPPLY.equals(operation)) {
                supplyTotal += amount;
            } else if (BUY.equals(operation)) {
                buyTotal += amount;
            }
        }

        int resultTotal = supplyTotal - buyTotal;

        return String.join(System.lineSeparator(),
                SUPPLY + COMMA + supplyTotal,
                BUY + COMMA + buyTotal,
                RESULT + COMMA + resultTotal);
    }

    private void writeToFile(String toFileName, String report) {
        try {
            Files.writeString(Path.of(toFileName), report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: " + toFileName, e);
        }
    }
}