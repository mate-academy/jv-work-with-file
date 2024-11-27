package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WorkWithFile {
    private static final String SUPPLY_KEY = "supply";
    private static final String BUY_KEY = "buy";
    private static final String RESULT_KEY = "result";
    private static final String SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        Map<String, Integer> data = getDataFromFile(fromFileName);
        createReport(toFileName, data);
    }

    private Map<String, Integer> getDataFromFile(String fromFileName) {
        Map<String, Integer> data = new HashMap<>();
        data.put(SUPPLY_KEY, 0);
        data.put(BUY_KEY, 0);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            bufferedReader.lines().forEach(line -> {
                String[] pair = line.split(SEPARATOR);
                if (pair.length == 2 && data.containsKey(pair[0])) {
                    data.put(pair[0], data.get(pair[0]) + Integer.parseInt(pair[1]));
                } else {
                    throw new RuntimeException("Invalid data format in file: " + fromFileName);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file " + fromFileName, e);
        }
        data.put(RESULT_KEY, calculateResult(data));

        return data;
    }

    private void createReport(String toFile, Map<String, Integer> data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            String text = SUPPLY_KEY + SEPARATOR + data.get(SUPPLY_KEY) + System.lineSeparator()
                    + BUY_KEY + SEPARATOR + data.get(BUY_KEY) + System.lineSeparator()
                    + RESULT_KEY + SEPARATOR + data.get(RESULT_KEY) + System.lineSeparator();
            bufferedWriter.write(text);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFile, e);
        }
    }

    private Integer calculateResult(Map<String, Integer> data) {
        Integer supply = null != data.get(SUPPLY_KEY) ? data.get(SUPPLY_KEY) : 0;
        Integer buy = null != data.get(BUY_KEY) ? data.get(BUY_KEY) : 0;
        return supply - buy;
    }
}
