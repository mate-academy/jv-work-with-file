package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supplyTotal = 0;
        int buyTotal = 0;

        try {
            List<String> lines = Files.readAllLines(Paths.get(fromFileName));
            for (String line : lines) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] parts = line.split(",");

                if (parts.length == 2) {
                    String operationType = parts[0].trim();
                    int amount = Integer.parseInt(parts[1].trim());

                    if (operationType.equals("supply")) {
                        supplyTotal += amount;
                    } else if (operationType.equals("buy")) {
                        buyTotal += amount;
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            throw new RuntimeException("Error", e);
        }

        int resultTotal = supplyTotal - buyTotal;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + supplyTotal);
            writer.newLine();
            writer.write("buy," + buyTotal);
            writer.newLine();
            writer.write("result," + resultTotal);
        } catch (IOException e) {
            throw new RuntimeException("Error writing data to file: " + toFileName, e);
        }
    }
}
