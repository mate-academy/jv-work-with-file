package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    private static final String CSV_DELIMITER = ",";
    private static final String SUPPLY_OPERATION = "supply";
    private static final String BUY_OPERATION = "buy";
    private static final int INITIAL_SUM = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> lines = readLines(fromFileName);
        int[] statistics = calculateStatistics(lines);
        String report = buildReport(statistics);
        writeReport(toFileName, report);
    }

    private List<String> readLines(String fileName) {
        try {
            return Files.readAllLines(Path.of(fileName));
        } catch (IOException e) {
            throw new RuntimeException("Error while reading file", e);
        }
    }

    private int[] calculateStatistics(List<String> lines) {
        int supplySum = INITIAL_SUM;
        int buySum = INITIAL_SUM;

        for (String line : lines) {
            String[] parts = line.split(CSV_DELIMITER);
            String operation = parts[0];
            int amount = Integer.parseInt(parts[1]);

            if (SUPPLY_OPERATION.equals(operation)) {
                supplySum += amount;
            } else if (BUY_OPERATION.equals(operation)) {
                buySum += amount;
            }
        }
        return new int[]{supplySum, buySum};
    }

    private String buildReport(int[] statistics) {
        int supply = statistics[0];
        int buy = statistics[1];
        int result = supply - buy;

        StringBuilder builder = new StringBuilder();
        builder.append(SUPPLY_OPERATION).append(CSV_DELIMITER)
            .append(supply).append(System.lineSeparator());
        builder.append(BUY_OPERATION).append(CSV_DELIMITER)
            .append(buy).append(System.lineSeparator());
        builder.append("result").append(CSV_DELIMITER)
            .append(result);

        return builder.toString();
    }

    private void writeReport(String fileName, String content) {
        try {
            Files.write(Path.of(fileName), content.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Error while writing file", e);
        }
    }
}
