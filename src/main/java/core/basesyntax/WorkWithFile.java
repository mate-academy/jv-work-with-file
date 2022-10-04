package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkWithFile {
    private static final String DELIMITER = ",";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final int KEY_POSITION = 0;
    private static final int VALUE_POSITION = 1;
    private static final String SUPPLY_KEY = "supply";
    private static final String BUY_KEY = "buy";
    private static final String RESULT_STRING = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        Map<String, Integer> csvContent;
        String report;
        try {
            csvContent = readCsvFile(Path.of(fromFileName));
            report = generateReport(csvContent);
        } catch (IOException | NumberFormatException e) {
            throw new RuntimeException("Unable to read file: " + e);
        }
        try {
            writeToCsv(Path.of(toFileName), report);
        } catch (IOException e) {
            throw new RuntimeException("Unable to write file: " + e);
        }
    }

    private Map<String, Integer> readCsvFile(Path path) throws IOException {
        List<String> lines = Files.readAllLines(path);
        Map<String, Integer> csvContent = new HashMap<>();
        for (String line : lines) {
            String key = line.split(DELIMITER)[KEY_POSITION];
            int value = Integer.parseInt(line.split(DELIMITER)[VALUE_POSITION]);
            if (csvContent.containsKey(key)) {
                int newValue = csvContent.get(key) + value;
                csvContent.put(key, newValue);
                continue;
            }
            csvContent.put(
                    line.split(DELIMITER)[KEY_POSITION],
                    Integer.parseInt(line.split(DELIMITER)[VALUE_POSITION])
            );
        }
        return csvContent;
    }

    private void writeToCsv(Path path, String report) throws IOException {
        Files.write(path, report.getBytes());
    }

    private String generateReport(Map<String, Integer> csvContent) {
        StringBuilder report = new StringBuilder();
        return report.append(SUPPLY_KEY).append(DELIMITER).append(csvContent.get(SUPPLY_KEY))
                .append(LINE_SEPARATOR)
                .append(BUY_KEY).append(DELIMITER).append(csvContent.get(BUY_KEY))
                .append(LINE_SEPARATOR)
                .append(RESULT_STRING).append(DELIMITER)
                .append(csvContent.get(SUPPLY_KEY) - csvContent.get(BUY_KEY))
                .toString();
    }
}
