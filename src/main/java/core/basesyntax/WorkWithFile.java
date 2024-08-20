package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        Map<String, Integer> operationSums = new LinkedHashMap<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String operation = parts[0].trim();
                    int amount = Integer.parseInt(parts[1].trim());
                    operationSums.put(operation, operationSums.getOrDefault(operation, 0) + amount);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        int supplySum = operationSums.getOrDefault("supply", 0);
        int buySum = operationSums.getOrDefault("buy", 0);
        int result = supplySum - buySum;
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            List<String> orderedKeys = new LinkedList<>();
            orderedKeys.add("supply");
            orderedKeys.add("buy");
            for (String key: orderedKeys) {
                bufferedWriter.write(key + "," + operationSums.get(key));
                bufferedWriter.newLine();
            }
            bufferedWriter.write("result," + result);
            bufferedWriter.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }
}
