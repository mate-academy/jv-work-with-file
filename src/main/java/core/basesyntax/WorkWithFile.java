package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int totalSupply = 0;
        int totalBuy = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String operation = parts[0];
                int amount = Integer.parseInt(parts[1]);

                if ("supply".equals(operation)) {
                    totalSupply += amount;
                } else if ("buy".equals(operation)) {
                    totalBuy += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + fromFileName, e);
        }

        List<String> report = new ArrayList<>();
        report.add("supply," + totalSupply);
        report.add("buy," + totalBuy);
        report.add("result," + (totalSupply - totalBuy));

        // Запис звіту у вихідний файл
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            for (String line : report) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file: " + toFileName, e);
        }
    }

}
