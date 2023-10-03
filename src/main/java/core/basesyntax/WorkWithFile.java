package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName));

            String line;
            int supplyTotal = 0;
            int buyTotal = 0;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String operationType = parts[0];
                    int amount = Integer.parseInt(parts[1]);

                    if (operationType.equals("supply")) {
                        supplyTotal += amount;
                    } else if (operationType.equals("buy")) {
                        buyTotal += amount;
                    }
                }
            }

            reader.close();

            int result = supplyTotal - buyTotal;

            writer.write("supply," + supplyTotal);
            writer.newLine();
            writer.write("buy," + buyTotal);
            writer.newLine();
            writer.write("result," + result);

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

