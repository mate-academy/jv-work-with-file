package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";
    private static final String NEW_LINE = System.lineSeparator();
    private static final int KEY_INDEX = 0;
    private static final int VALUE_INDEX = 1;
    private static final int DEFAULT_VALUE = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        Map<String, Integer> map = new TreeMap<>(Comparator.reverseOrder());
        readDataIntoMap(fromFileName, map);
        writeDataToFile(toFileName, map);
    }

    private void readDataIntoMap(String fileName, Map<String, Integer> map) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                updateMapWithLine(map, line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fileName, e);
        }
    }

    private void updateMapWithLine(Map<String, Integer> map, String line) {
        String[] parts = line.split(COMMA);
        String key = parts[KEY_INDEX];
        Integer value = Integer.parseInt(parts[VALUE_INDEX]);
        map.merge(key, value, Integer::sum);
    }

    private void writeDataToFile(String fileName, Map<String, Integer> map) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                writer.write(entry.getKey() + COMMA + entry.getValue() + NEW_LINE);
            }
            writer.write(RESULT + COMMA + calculateResult(map));
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file " + fileName, e);
        }
    }

    private int calculateResult(Map<String, Integer> map) {
        int resultSupply = map.getOrDefault(SUPPLY, DEFAULT_VALUE);
        int resultBuy = map.getOrDefault(BUY, DEFAULT_VALUE);
        return resultSupply - resultBuy;
    }
}
