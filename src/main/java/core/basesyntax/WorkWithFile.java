package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class WorkWithFile {
    private static final String SUPPLY_KEY = "supply";
    private static final String BUY_KEY = "buy";
    private static final String RESULT_KEY = "result";

    private void collectStatistics(String[] data, HashMap<String, Integer> map) {
        if (data != null && data.length == 2) {
            String key = data[0];
            int value = Integer.parseInt(data[1]);
            map.merge(key, value, Integer::sum);
        }
    }

    private void calculateResult(HashMap<String, Integer> map) {
        if (map != null && map.containsKey(SUPPLY_KEY) && map.containsKey(BUY_KEY)) {
            int result = map.get(SUPPLY_KEY) - map.get(BUY_KEY);
            map.put(RESULT_KEY, result);
        }
    }

    private void writeFile(String toFileName, HashMap<String, Integer> map) {
        File toFile = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter(toFile))) {
            StringBuilder builder = new StringBuilder();
            builder.append(SUPPLY_KEY).append(",").append(map.get(SUPPLY_KEY))
                    .append(System.lineSeparator()).append(BUY_KEY).append(",")
                    .append(map.get(BUY_KEY)).append(System.lineSeparator())
                    .append(RESULT_KEY).append(",").append(map.get(RESULT_KEY))
                    .append(System.lineSeparator());
            bufferedWriter.write(builder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file: ", e);
        }
    }

    public void getStatistic(String fromFileName, String toFileName) throws RuntimeException {
        HashMap<String, Integer> map = new HashMap<>();
        File fromFile = new File(fromFileName);
        if (!fromFile.exists()) {
            throw new RuntimeException();
        }
        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader(fromFile))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split(",");
                collectStatistics(words, map);
            }
            calculateResult(map);
        } catch (IOException | RuntimeException e) {
            throw new RuntimeException("Can't read data from file: ", e);
        }
        writeFile(toFileName, map);
    }
}
