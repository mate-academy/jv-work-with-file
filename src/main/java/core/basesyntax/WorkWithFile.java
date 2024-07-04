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
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
                BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            String value;
            while ((value = reader.readLine()) != null) {
                String[] parts = value.split(",");
                String operationType = parts[0].trim();
                int amount = Integer.parseInt(parts[1].trim());
                if ("supply".equals(operationType)) {
                    supplyTotal += amount;
                } else if ("buy".equals(operationType)) {
                    buyTotal += amount;
                }
            }
            int result = supplyTotal - buyTotal;
            writer.write("supply," + supplyTotal);
            writer.newLine();
            writer.write("buy," + buyTotal);
            writer.newLine();
            writer.write("result," + result);
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
