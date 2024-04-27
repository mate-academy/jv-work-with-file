package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
             BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {

            String line;
            int supplyTotal = 0;
            int buyTotal = 0;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int amount = Integer.parseInt(data[1]);
                if (data[0].equals("supply")) {
                    supplyTotal += amount;
                } else if (data[0].equals("buy")) {
                    buyTotal += amount;
                }
            }

            int result = supplyTotal - buyTotal;
            writer.write("supply," + supplyTotal + "\n");
            writer.write("buy," + buyTotal + "\n");
            writer.write("result," + result);

        } catch (IOException e) {
            throw new RuntimeException("Can't read or write file", e);
        }
    }
}
