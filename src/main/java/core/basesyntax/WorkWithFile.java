package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WorkWithFile {
    private static final String DELIMITER = ",";
    private static final int OPERATION_INDEX = 0;
    private static final int VALUE_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> data = readFile(fromFileName);
        String report = generateReport(data);
        writeToFile(report, toFileName);
    }

    private List<String> readFile(String fileName) {
        List<String> data;
        File file = new File(fileName);

        try {
            data = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fileName, e);
        }

        return data;
    }

    private String generateReport(List<String> data) {
        Map<String, Integer> result = new LinkedHashMap<>();

        for (String fileInfoItem : data) {
            String[] splitFileInfoItem = fileInfoItem.toLowerCase().split(DELIMITER);
            String operation = splitFileInfoItem[OPERATION_INDEX];
            int value = Integer.parseInt(splitFileInfoItem[VALUE_INDEX]);

            if (result.containsKey(operation)) {
                result.computeIfPresent(operation, (k, v) -> v + value);
            } else {
                result.put(operation, value);
            }
        }

        int total = result.get("supply") - result.get("buy");
        result.put("result", total);

        StringBuilder report = new StringBuilder();
        report.append("supply").append(DELIMITER);
        report.append(result.get("supply")).append(System.lineSeparator());
        report.append("buy").append(DELIMITER);
        report.append(result.get("buy")).append(System.lineSeparator());
        report.append("result").append(DELIMITER);
        report.append(result.get("result")).append(System.lineSeparator());

        return report.toString();
    }

    private void writeToFile(String report, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(fileName)))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + fileName, e);
        }
    }
}
