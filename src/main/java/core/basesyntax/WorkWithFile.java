package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    private static final String SEPARATOR = ",";
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> lines = readFromFile(fromFileName);
        int[] totals = calculateTotals(lines);
        List<String> report = buildReport(totals[0], totals[1]);
        writeToFile(toFileName, report);
    }

    private List<String> readFromFile(String fileName) {
        try {
            return Files.readAllLines(Path.of(fileName));
        } catch (IOException e) {
            throw new RuntimeException("Error reading file", e);
        }
    }

    private int[] calculateTotals(List<String> lines) {
        int supplyTotal = 0;
        int buyTotal = 0;

        for (String line : lines) {
            String[] parts = line.split(SEPARATOR);
            String operation = parts[OPERATION_INDEX];
            int amount = Integer.parseInt(parts[AMOUNT_INDEX]);

            if (SUPPLY.equals(operation)) {
                supplyTotal += amount;
            } else if (BUY.equals(operation)) {
                buyTotal += amount;
            }
        }

        return new int[]{supplyTotal, buyTotal};
    }

    private List<String> buildReport(int supplyTotal, int buyTotal) {
        int result = supplyTotal - buyTotal;

        List<String> report = new ArrayList<>();
        report.add(SUPPLY + SEPARATOR + supplyTotal);
        report.add(BUY + SEPARATOR + buyTotal);
        report.add(RESULT + SEPARATOR + result);

        return report;
    }

    private void writeToFile(String fileName, List<String> data) {
        try {
            Files.write(Path.of(fileName), data);
        } catch (IOException e) {
            throw new RuntimeException("Error writing file", e);
        }
    }
}
