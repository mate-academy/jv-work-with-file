package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;


public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        HashMap<String, Integer> statistic = new HashMap<String, Integer>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String operations = parts[0];
                int sum = Integer.parseInt(parts[1]);
                statistic.put(operations, statistic.getOrDefault(operations, 0) + sum);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading file", e);
        }
        int supplyTotal = statistic.getOrDefault("supply", 0);
        int buyTotal = statistic.getOrDefault("buy", 0);
        int result = supplyTotal - buyTotal;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + supplyTotal);
            writer.newLine();
            writer.write("buy," + buyTotal);
            writer.newLine();
            writer.write("result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Error writing file", e);
        }
    }
}

