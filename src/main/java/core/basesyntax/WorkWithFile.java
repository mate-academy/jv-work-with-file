package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";
    private static final String DELIMITER = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String report = getReport(readLinesFromFile(fromFileName));
        saveStatistics(toFileName, report);
    }

    public void saveStatistics(String toFileName, String statistics) {
        try {
            Files.writeString(Path.of(toFileName), statistics);
        } catch (IOException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    private Stream<String> readLinesFromFile(String fromFileName) {
        try {
            return Files.lines(Path.of(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Error while reading from file: " + e.getMessage());
        }
    }

    private String getReport(Stream<String> lines) {
        Map<String, Integer> operationCounts = new HashMap<>();

        lines.map(line -> line.split(DELIMITER))
                .forEach(parts -> {
                    String operation = parts[0];
                    int value = Integer.parseInt(parts[1].trim());
                    operationCounts
                            .compute(operation, (key, oldValue)
                                    -> oldValue == null ? value : oldValue + value);
                });

        int supplyTotal = operationCounts.getOrDefault("supply", 0);
        int buyTotal = operationCounts.getOrDefault("buy", 0);
        int result = supplyTotal - buyTotal;

        StringBuilder statistics = new StringBuilder();
        statistics
                .append("supply")
                .append(DELIMITER)
                .append(supplyTotal)
                .append(System.lineSeparator());

        statistics
                .append("buy")
                .append(DELIMITER)
                .append(buyTotal)
                .append(System.lineSeparator());

        statistics
                .append("result")
                .append(DELIMITER)
                .append(result);

        return statistics.toString();
    }
}
