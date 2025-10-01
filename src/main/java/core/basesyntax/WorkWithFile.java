package core.basesyntax;

import java.io.*;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFile, String toFile) {
        int supplyTotal = 0;
        int buyTotal = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 2) {
                    continue;
                }
                String operation = parts[0].trim();
                int amount = Integer.parseInt(parts[1].trim());

                if (operation.equals("supply")) {
                    supplyTotal += amount;
                } else if (operation.equals("buy")) {
                    buyTotal += amount;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file" + e.getMessage());
        }
        int result = supplyTotal - buyTotal;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFile))) {
            writer.write("supply," + supplyTotal);
            writer.newLine();
            writer.write("buy," + buyTotal);
            writer.newLine();
            writer.write("result," + result);
        } catch (IOException e) {
            System.out.println("Error writing file" + e.getMessage());
        }
    }

}
