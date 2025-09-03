package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supplySum = 0;
        int buySum = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    int amount = Integer.parseInt(parts[1].trim());
                    if (parts[0].equals("supply")) {
                        supplySum += amount;
                    } else if (parts[0].equals("buy")) {
                        buySum += amount;
                    }
                }
            }
            int result = supplySum - buySum;
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
                writer.write("supply," + supplySum);
                writer.newLine();
                writer.write("buy," + buySum);
                writer.newLine();
                writer.write("result," + result);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't process file: " + fromFileName + " or "
                    + toFileName, e);
        }
    }
}
