package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(fromFileName)));
             BufferedWriter writer = new BufferedWriter(new FileWriter(new File(toFileName)))) {

            int supplyTotal = 0;
            int buyTotal = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String operation = parts[0].trim();
                int amount = Integer.parseInt(parts[1].trim());

                if ("supply".equals(operation)) {
                    supplyTotal += amount;
                } else if ("buy".equals(operation)) {
                    buyTotal += amount;
                }
            }

            int result = supplyTotal - buyTotal;

            writer.write("supply," + supplyTotal);
            writer.newLine();
            writer.write("buy," + buyTotal);
            writer.newLine();
            writer.write("result," + result);

        } catch (IOException e) {
            throw new RuntimeException("Error processing files", e);
        }
    }
}
