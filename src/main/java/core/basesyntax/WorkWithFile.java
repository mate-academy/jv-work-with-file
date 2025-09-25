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
    public static final String dataSeparator = ",";
    public static final String RESULT = "result";
    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> lines = readFromFile(fromFileName);
        Map<String, Integer> agregatedData = createAggregatedData(lines);
        String report = createReport(agregatedData);
        savetoFile(report, toFileName);
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
                .append(dataSeparator)
                .append(value)
                .append(System.lineSeparator());
        return stringBuilder.toString();
    }

    private void savetoFile(String report, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("can't write to the file", e);
        }
    }

    private Map<String, Integer> createAggregatedData(List<String> lines) {
        Map<String, Integer> dataforReport = new HashMap<>();
        for (String line : lines) {
            String[] dataInLine = line.split(dataSeparator);
            if (dataforReport.containsKey(dataInLine[0])) {
                Integer temp = dataforReport.get(dataInLine[0]);
                temp += Integer.valueOf(dataInLine[1]);
                dataforReport.put(dataInLine[0], temp);
            } else {
                dataforReport.put(dataInLine[0], Integer.valueOf(dataInLine[1]));
            }
        }
        dataforReport.put(RESULT, getResult(dataforReport));
        return dataforReport;
    }

    private Integer getResult(Map<String, Integer> dataforReport) {
        return dataforReport.get(SUPPLY) - dataforReport.get(BUY);
    }

    private List<String> readFromFile(String fromFileName) {
        Path path = Path.of(fromFileName);

        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
