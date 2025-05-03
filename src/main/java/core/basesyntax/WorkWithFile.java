package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkWithFile {
    private static final String SUPPLY_KEY = "supply";
    private static final String BUY_KEY = "buy";
    private static final String RESULT_KEY = "result";
    private static final String SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> textLines = getInfosFromFile(fromFileName);
        Map<String, Integer> data = getDataFromFile(textLines, fromFileName);
        String reportText = transformDataToReport(data);
        createReport(toFileName, reportText);
    }

    private List<String> getInfosFromFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            return bufferedReader.lines().toList();
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file " + fromFileName, e);
        }
    }

    private Map<String, Integer> getDataFromFile(List<String> textLines, String fromFileName) {
        Map<String, Integer> data = new HashMap<>();
        data.put(SUPPLY_KEY, 0);
        data.put(BUY_KEY, 0);

        for (String line : textLines) {
            String[] pair = line.split(SEPARATOR);
            if (pair.length == 2 && data.containsKey(pair[0])) {
                data.put(pair[0], data.get(pair[0]) + Integer.parseInt(pair[1]));
            } else {
                throw new RuntimeException("Invalid data format in file " + fromFileName);
            }
        }
        data.put(RESULT_KEY, data.get(SUPPLY_KEY) - data.get(BUY_KEY));
        return data;
    }

    private String transformDataToReport(Map<String, Integer> data) {
        return SUPPLY_KEY + SEPARATOR + data.get(SUPPLY_KEY) + System.lineSeparator()
                + BUY_KEY + SEPARATOR + data.get(BUY_KEY) + System.lineSeparator()
                + RESULT_KEY + SEPARATOR + data.get(RESULT_KEY) + System.lineSeparator();
    }

    private void createReport(String toFile, String text) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            bufferedWriter.write(text);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFile, e);
        }
    }
}
