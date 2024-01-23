package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supplyTotal = 0;
        int buyTotal = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    int quantity = Integer.parseInt(parts[1].trim());
                    if ("supply".equals(parts[0].trim())) {
                        supplyTotal += quantity;
                    } else if ("buy".equals(parts[0].trim())) {
                        buyTotal += quantity;
                    }
                }
            }
        } catch (IOException ex) {
            throw new RuntimeException("Error reading file", ex);
        }

        int result = supplyTotal - buyTotal;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + supplyTotal);
            writer.newLine();
            writer.write("buy," + buyTotal);
            writer.newLine();
            writer.write("result," + result);
        } catch (IOException ex) {
            throw new RuntimeException("Error writing to file", ex);
        }
    }
}
