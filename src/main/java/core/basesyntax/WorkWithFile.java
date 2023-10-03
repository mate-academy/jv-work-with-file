package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {

                int supplyTotal = 0;
                int buyTotal = 0;
                String line;

                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 2) {
                        String operationType = parts[0];
                        int amount = Integer.parseInt(parts[1]);

                        if ("supply".equals(operationType)) {
                            supplyTotal += amount;
                        } else if ("buy".equals(operationType)) {
                            buyTotal += amount;
                        }
                    }
                }

                int result = supplyTotal - buyTotal;

                writer.write("supply," + supplyTotal + System.getProperty("line.separator"));
                writer.write("buy," + buyTotal + System.getProperty("line.separator"));
                writer.write("result," + result + System.getProperty("line.separator"));

            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading or writing files: "
                    + fromFileName + ", " + toFileName, e);
        }
    }
}
