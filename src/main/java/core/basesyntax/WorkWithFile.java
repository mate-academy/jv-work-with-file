package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class WorkWithFile {
    private static final String COLUMN_SEPARATOR = ",";
    private static final String[] OPERATION_TYPES = {"supply", "buy"};

    public void getStatistic(String fromFileName, String toFileName) {
        String data = getDataFromFile(fromFileName);
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

    private String getDataFromFile(String fileName) {
        StringBuilder data = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String nextLine = bufferedReader.readLine();
            while (nextLine != null) {
                data.append(nextLine).append(System.lineSeparator());
                nextLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data.toString();
    }

    private void putDataToFile(String fileName, String data) {
        File file = new File(fileName);
        try (FileWriter fileWriter = new FileWriter(file)) {
            if (!file.exists()) {
                file.createNewFile();
            }
            fileWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private HashMap<String, Integer> parseDataTpHashMap(String data) {
        HashMap<String, Integer> statistic = new HashMap<>();
        statistic.put("supply", 0);
        statistic.put("buy", 0);
        String[] rows = data.split(System.lineSeparator());
        String[] column;
        int value;
        for (String row : rows) {
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
