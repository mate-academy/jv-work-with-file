package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkWithFile {
    private static final String DELIMITER = ",";
    private static final int KEY_POSITION = 0;
    private static final int VALUE_POSITION = 1;
    private static final String SUPPLY_KEY = "supply";
    private static final String BUY_KEY = "buy";
    private static final String RESULT_STRING = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        writeToCsv(Path.of(toFileName), generateReport(readCsvFile(Path.of(fromFileName))));
    }

    private List<String> readCsvFile(Path path) {
        List<String> lines;
        try {
            lines = Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException("Unable to read file: " + e);
        }
        return lines;
    }

    private void writeToCsv(Path path, String report) {
        try {
            Files.write(path, report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Unable to write file: " + e);
        }
    }

    private String generateReport(List<String> csvContent) {
        Map<String, Integer> summary = new HashMap<>();
        for (String line : csvContent) {
            String key = line.split(DELIMITER)[KEY_POSITION];
            int value = Integer.parseInt(line.split(DELIMITER)[VALUE_POSITION]);
            if (summary.containsKey(key)) {
                int newValue = summary.get(key) + value;
                summary.put(key, newValue);
                continue;
            }
            summary.put(
                    line.split(DELIMITER)[KEY_POSITION],
                    Integer.parseInt(line.split(DELIMITER)[VALUE_POSITION])
            );
        }
        StringBuilder report = new StringBuilder();
        return report.append(SUPPLY_KEY).append(DELIMITER).append(summary.get(SUPPLY_KEY))
                .append(System.lineSeparator())
                .append(BUY_KEY).append(DELIMITER).append(summary.get(BUY_KEY))
                .append(System.lineSeparator())
                .append(RESULT_STRING).append(DELIMITER)
                .append(summary.get(SUPPLY_KEY) - summary.get(BUY_KEY))
                .toString();
    }
}
