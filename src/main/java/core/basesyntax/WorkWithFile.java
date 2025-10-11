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

        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String operation = parts[0].trim();
                    int amount = Integer.parseInt(parts[1].trim());
                    if ("supply".equalsIgnoreCase(operation)) {
                        supplyTotal += amount;
                    } else if ("buy".equalsIgnoreCase(operation)) {
                        buyTotal += amount;
                    }
                }
            }
        } catch (NumberFormatException | IOException e) {
            System.err.println("Error reading file: " + ((Exception)e).getMessage());
            return;
        }

        int result = supplyTotal - buyTotal;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + supplyTotal);
            writer.newLine();
            writer.write("buy," + buyTotal);
            writer.newLine();
            writer.write("result," + result);
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }

    }
}
