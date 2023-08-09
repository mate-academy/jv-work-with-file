package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WorkWithFile {
    private static final String DATA_SEPARATOR = ",";
    private static final String SUPPLY_OPERATION = "supply";
    private static final String BUY_OPERATION = "buy";
    private static final String REPORT_RESULT_ROW = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        Map<String, Integer> fileData = readFromFile(fromFileName);
        String report = getReport(fileData);
        writeReportToFile(report, toFileName);
    }

    private Map<String, Integer> readFromFile(String fileName) {
        List<String> dataFromFile = new ArrayList<>();
        try {
            dataFromFile.addAll(Files.readAllLines(new File(fileName).toPath()));
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file", e);
        }
        return dataFromFile.stream()
                .collect(Collectors.toMap(
                        s -> s.substring(0, s.indexOf(DATA_SEPARATOR)),
                        s -> Integer.valueOf(s.substring(s.indexOf(DATA_SEPARATOR) + 1)),
                        Integer::sum
                ));
    }

    private String getReport(Map<String, Integer> data) {
        int result = data.get(SUPPLY_OPERATION) - data.get(BUY_OPERATION);
        return String.join(System.lineSeparator(),
                formatData(SUPPLY_OPERATION, data.get(SUPPLY_OPERATION).toString()),
                formatData(BUY_OPERATION, data.get(BUY_OPERATION).toString()),
                formatData(REPORT_RESULT_ROW, String.valueOf(result)));
    }

    private void writeReportToFile(String report, String fileTo) {
        try {
            Files.write(new File(fileTo).toPath(), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }

    private String formatData(String... data) {
        return String.join(DATA_SEPARATOR, data);
    }
}
