package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

public class WorkWithFile {
    private static final String COLUMN_SEPARATOR = ",";
    private static final String[] OPERATION_TYPES = {"supply", "buy"};

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> data = getDataFromFile(fromFileName);
        HashMap<String, Integer> statistic = parseDataTpHashMap(data);
        StringBuilder resultData = new StringBuilder();
        for (String operationType : OPERATION_TYPES) {
            resultData.append(operationType).append(COLUMN_SEPARATOR)
                    .append(statistic.get(operationType)).append(System.lineSeparator());
        }
        int result = statistic.get(OPERATION_TYPES[0]) - statistic.get(OPERATION_TYPES[1]);
        resultData.append("result").append(COLUMN_SEPARATOR).append(result);
        putDataToFile(toFileName, resultData.toString());
    }

    private List<String> getDataFromFile(String fileName) {
        File file = new File(fileName);
        List<String> data;
        try {
            data = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    private void putDataToFile(String fileName, String data) {
        try {
            Files.write(Path.of(fileName), data.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private HashMap<String, Integer> parseDataTpHashMap(List<String> data) {
        HashMap<String, Integer> statistic = new HashMap<>();
        statistic.put("supply", 0);
        statistic.put("buy", 0);
        String[] column;
        int value;
        for (String row : data) {
            column = row.split(COLUMN_SEPARATOR);
            value = Integer.parseInt(column[1]);
            if (column[0].equals(OPERATION_TYPES[0])) {
                statistic.replace(OPERATION_TYPES[0], statistic.get(OPERATION_TYPES[0]) + value);
            } else {
                statistic.replace(OPERATION_TYPES[1], statistic.get(OPERATION_TYPES[1]) + value);
            }
        }
        return statistic;
    }
}
