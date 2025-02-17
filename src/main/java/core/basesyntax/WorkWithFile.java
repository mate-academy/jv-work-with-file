package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        File toFile = new File(toFileName);
        Map<String, Integer> statistic = new HashMap<>();
        try (BufferedReader br = Files.newBufferedReader(fromFile.toPath())) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String operation = parts[0];
                int amount = Integer.parseInt(parts[1]);
                statistic.merge(operation, amount, Integer::sum);
            }
        } catch (IOException n) {
            throw new RuntimeException("Read data from file error", n);
        }
        int result = statistic.getOrDefault("supply", 0) - statistic.getOrDefault("buy", 0);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + statistic.getOrDefault("supply", 0));
            writer.newLine();
            writer.write("buy," + statistic.getOrDefault("buy", 0));
            writer.newLine();
            writer.write("result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Write data to file error", e);
        }
    }
}
