package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);

        if (!file.exists()) {
            System.out.println("the file does not exist.");
            return;
        }

        int supplyTotal = 0;
        int buyTotal = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String operation = parts[0];
                int amount = Integer.parseInt(parts[1]);

                if (operation.equals("supply")) {
                    supplyTotal += amount;
                } else if (operation.equals("buy")) {
                    buyTotal += amount;
                }
            }            
        } catch (IOException e) {
            System.out.println("Error while reading file: " + e.getMessage());
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
            System.out.println("Error while writing file: " + e.getMessage());
            return;
        }
    }
}
