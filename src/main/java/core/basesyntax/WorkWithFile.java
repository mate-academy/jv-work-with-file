package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        Map<String, Integer> operationMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String operationType = parts[0].trim();
                    int amount = Integer.parseInt(parts[1].trim());

                    operationMap.put(operationType, operationMap.getOrDefault(operationType,
                            0) + amount);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading data from file " + fromFileName, e);
        }

        int supply = operationMap.getOrDefault("supply", 0);
        int buy = operationMap.getOrDefault("buy", 0);
        int result = supply - buy;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + supply);
            writer.newLine();
            writer.write("buy," + buy);
            writer.newLine();
            writer.write("result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Error writing data to file " + toFileName, e);
        }
    }
}
