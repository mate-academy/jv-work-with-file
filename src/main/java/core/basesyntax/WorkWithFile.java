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
        int supplyCount = 0;
        int buyCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length != 2) {
                    throw new RuntimeException("Invalid input format");
                }
                String type = fields[0];
                int amount = Integer.parseInt(fields[1]);
                if (type.equals("supply")) {
                    supplyTotal += amount;
                    supplyCount++;
                } else if (type.equals("buy")) {
                    buyTotal += amount;
                    buyCount++;
                } else {
                    throw new RuntimeException("Invalid input type: " + type);
                }
            }

            writer.write("supply," + supplyTotal + System.lineSeparator());
            writer.write("buy," + buyTotal + System.lineSeparator());
            writer.write("result," + (supplyTotal - buyTotal) + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Something went wrong :(( ");
        }
    }
}
