package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
                BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, true))) {

            int buyTotal = 0;
            int sellTotal = 0;

            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 2) {
                    String operationType = fields[0].trim().toLowerCase();
                    int amount = Integer.parseInt(fields[1].trim());

                    if ("buy".equals(operationType)) {
                        buyTotal += amount;
                    } else if ("sell".equals(operationType)) {
                        sellTotal += amount;
                    }
                }
            }

            final int result = sellTotal - buyTotal;

            writer.write("sell," + sellTotal);
            writer.newLine();
            writer.write("buy," + buyTotal);
            writer.newLine();
            writer.write("result," + result);

        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + fromFileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Error reading or writing file", e);
        }
    }
}
