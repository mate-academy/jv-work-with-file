package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(fromFileName, toFileName);

    }

    private HashMap<String, Integer> readFromFile(String readFrormFile) {
        HashMap<String, Integer> statistic = new HashMap<String, Integer>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(readFrormFile));
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
        return statistic;
    }

    private void writeToFile(String readFromFileName, String writeToFileName) {
        HashMap<String, Integer> temp = readFromFile(readFromFileName);
        int supplyTotal = temp.getOrDefault("supply", 0);
        int buyTotal = temp.getOrDefault("buy", 0);
        int result = supplyTotal - buyTotal;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(writeToFileName))) {
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

