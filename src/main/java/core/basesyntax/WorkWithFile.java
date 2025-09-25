package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkWithFile {
    private static final String DATA_SEPARATOR = ",";
    private static final String RESULT = "result";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> lines = readFromFile(fromFileName);
        Map<String, Integer> aggregatedData = createAggregatedData(lines);
        String report = createReport(aggregatedData);
        saveToFile(report, toFileName);
    }

    private String createReport(Map<String, Integer> dataForReport) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(createLine(SUPPLY, dataForReport.get(SUPPLY)));
        stringBuilder.append(createLine(BUY, dataForReport.get(BUY)));
        stringBuilder.append(createLine(RESULT, dataForReport.get(RESULT)));
        return stringBuilder.toString();
    }

    private String createLine(String key, Integer value) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(key)
                .append(DATA_SEPARATOR)
                .append(value)
                .append(System.lineSeparator());
        return stringBuilder.toString();
    }

    private void saveToFile(String report, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("can't write to the file", e);
        }
    }

    private Map<String, Integer> createAggregatedData(List<String> lines) {
        Map<String, Integer> dataforReport = new HashMap<>();
        dataforReport.put(BUY, 0);
        dataforReport.put(SUPPLY, 0);
        for (String line : lines) {
            String[] dataInLine = line.split(DATA_SEPARATOR);
            if (dataInLine.length < 2) {
                continue;
            }
            String key = dataInLine[0].trim();
            if (!BUY.equals(key) && !SUPPLY.equals(key)) {
                throw new RuntimeException(key + "is not a " + BUY + " or" + SUPPLY);
            }
            int value = 0;
            try {
                value = Integer.parseInt(dataInLine[1].trim());
            } catch (NumberFormatException e) {
                throw new RuntimeException(dataInLine[1] + "is not a number", e);
            }
            if (dataforReport.containsKey(key)) {
                Integer temp = dataforReport.get(key);
                temp += value;
                dataforReport.put(key, temp);
            } else {
                dataforReport.put(key, value);
            }
        }
        dataforReport.put(RESULT, getResult(dataforReport));
        return dataforReport;
    }

    private Integer getResult(Map<String, Integer> dataForReport) {
        return dataForReport.get(SUPPLY) - dataForReport.get(BUY);
    }

    private List<String> readFromFile(String fromFileName) {
        Path path = Path.of(fromFileName);

        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
    }
}
