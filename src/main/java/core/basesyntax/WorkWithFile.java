package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";
    private static final int INDEX_SUPPLY = 0;
    private static final int INDEX_BUY = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> data = readFile(fromFileName);
        String report = generateReport(data);
        writeToFile(toFileName, report);
    }

    private List<String> readFile(String fileName) {
        try {
            return Files.readAllLines(Path.of(fileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fileName, e);
        }
    }

    private String generateReport(List<String> data) {
        int[] statistics = calculateStatistic(data);
        return buildReportString(statistics[INDEX_SUPPLY], statistics[INDEX_BUY]);
    }

    private int[] calculateStatistic(List<String> data) {
        int supplyResult = 0;
        int buyResult = 0;

        for (String line : data) {
            String[] parts = line.split(COMMA);
            String operation = parts[0];
            int amount = Integer.parseInt(parts[1]);

            if (SUPPLY.equals(operation)) {
                supplyResult += amount;
            } else if (BUY.equals(operation)) {
                buyResult += amount;
            }
        }

        return new int[]{supplyResult, buyResult};
    }

    private String buildReportString(int supplyResult, int buyResult) {
        int result = supplyResult - buyResult;

        return new StringBuilder()
                .append(SUPPLY).append(COMMA).append(supplyResult)
                .append(System.lineSeparator())
                .append(BUY).append(COMMA).append(buyResult)
                .append(System.lineSeparator())
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

    public static void main(String[] args) {
        WorkWithFile workwithfile = new WorkWithFile();
        workwithfile.getStatistic("banana.csv", "bananaResult.csv");
    }
}
