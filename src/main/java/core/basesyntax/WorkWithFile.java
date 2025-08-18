package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
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
        List<String> report = createReport(agregatedData);
        savetoFile(report, toFileName);
    }

    private List<String> createReport(Map<String, Integer> dataForReport) {
        List<String> report = new ArrayList<>();
        report.add(createLine(SUPPLY, dataForReport.get(SUPPLY)));
        report.add(createLine(BUY, dataForReport.get(BUY)));
        report.add(createLine(RESULT, dataForReport.get(RESULT)));
        return report;
    }

    private String createLine(String key, Integer value) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(key)
                .append(dataSeparator)
                .append(value)
                .append(System.lineSeparator());
        return stringBuilder.toString();
    }

    private void savetoFile(List<String> report, String toFileName) {
        File file = new File(toFileName);
        try {
            file.delete();
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (String line : report) {
            try {
                Files.write(file.toPath(), line.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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
