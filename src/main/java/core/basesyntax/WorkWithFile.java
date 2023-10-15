package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";
    private static final String DELIMITER = ",";
    private static final int DEFAULT_VALUE = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        Map<String, Integer> operationCounts = getInfoFromFile(fromFileName);
        saveStatistics(toFileName, getStatistics(operationCounts));
    }

    public String getStatistics(Map<String, Integer> operationCounts) {
        int supplyTotal = operationCounts.getOrDefault(SUPPLY, DEFAULT_VALUE);
        int buyTotal = operationCounts.getOrDefault(BUY, DEFAULT_VALUE);
        int result = supplyTotal - buyTotal;

        StringBuilder statistics = new StringBuilder();
        statistics
                .append(SUPPLY)
                .append(DELIMITER)
                .append(supplyTotal)
                .append(System.lineSeparator());

        statistics
                .append(BUY)
                .append(DELIMITER)
                .append(buyTotal)
                .append(System.lineSeparator());

        statistics
                .append(RESULT)
                .append(DELIMITER)
                .append(result);

        return statistics.toString();
    }

    public void saveStatistics(String toFileName, String statistics) {
        try {
            Files.writeString(Path.of(toFileName), statistics.toString());

            System.out.println("Statistics successfully written to " + toFileName);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public Map<String, Integer> getInfoFromFile(String fromFileName) {
        Map<String, Integer> operationCounts = new HashMap<>();

        try (var lines = Files.lines(Path.of(fromFileName))) {
            lines.map(line -> line.split(DELIMITER))
                    .forEach(parts -> {
                        String operation = parts[0];
                        int value = Integer.parseInt(parts[1].trim());
                        operationCounts
                                .put(
                                        operation,
                                        operationCounts
                                                .getOrDefault(operation, DEFAULT_VALUE)
                                                + value);
                    });

            return operationCounts;
        } catch (IOException e) {
            System.out.println("Error while reading from file: " + e.getMessage());
            return operationCounts;
        }
    }
}

