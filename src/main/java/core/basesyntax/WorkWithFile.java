package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        writeDataToFile(getDataFromFile(fromFileName), toFileName);
    }

    private static Map<String, Integer> getDataFromFile(String fromFileName) {
        Map<String, Integer> map = new TreeMap<>(Collections.reverseOrder());
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            int coma;
            int oldMapValue;
            int newMapValue;

            while ((line = bufferedReader.readLine()) != null) {
                coma = line.indexOf(',');
                if (map.containsKey(line.substring(0, coma))) {
                    oldMapValue = map.get(line.substring(0, coma));
                    newMapValue = oldMapValue
                            + Integer.parseInt(line.substring(coma + 1));
                    map.put(line.substring(0, coma), newMapValue);
                    continue;
                }
                map.put(line.substring(0, coma),
                        Integer.parseInt(line.substring(coma + 1)));
            }
            return map;
        } catch (IOException e) {
            throw new RuntimeException("Cannot read from .csv file " + fromFileName + e);
        }
    }

    private static void writeDataToFile(Map<String, Integer> data, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            int result = data.get("supply") - data.get("buy");

            for (Map.Entry<String, Integer> entry : data.entrySet()) {
                bufferedWriter.write(entry.getKey() + ","
                        + entry.getValue() + System.lineSeparator());
            }
            bufferedWriter.write("result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write to .csv file " + toFileName + e);
        }
    }

    public static void main(String[] args) {
        WorkWithFile workWithFile = new WorkWithFile();
        workWithFile.getStatistic("apple.csv", "report.csv");

    }
}
