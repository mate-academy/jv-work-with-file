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
        Map<String, Integer> statistics = readData(fromFileName);
        writeData(toFileName, statistics);
    }

    private Map<String, Integer> readData(String fileName) {
        Map<String, Integer> statistics = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 2) {
                    continue;
                }

                String operationType = parts[0].trim();
                int amount;
                try {
                    amount = Integer.parseInt(parts[1].trim());
                } catch (NumberFormatException e) {
                    continue;
                }

                statistics.put(operationType, statistics.getOrDefault(operationType, 0) + amount);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read that file", e);
        }

        return statistics;
    }

    private void writeData(String fileName, Map<String, Integer> statistics) {
        int supply = statistics.getOrDefault("supply", 0);
        int buy = statistics.getOrDefault("buy", 0);
        int result = supply - buy;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("supply," + supply);
            writer.newLine();
            writer.write("buy," + buy);
            writer.newLine();
            writer.write("result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to that file", e);
        }
    }
}
