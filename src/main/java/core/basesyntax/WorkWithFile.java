package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int totalSupply = 0;
        int totalBuy = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 2) {
                    String operationType = fields[0].trim();
                    int amount = Integer.parseInt(fields[1].trim());
                    if (operationType.equalsIgnoreCase("supply")) {
                        totalSupply += amount;
                    } else if (operationType.equalsIgnoreCase("buy")) {
                        totalBuy += amount;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("something went wrong");
        }

        int result = totalSupply - totalBuy;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + totalSupply);
            writer.newLine();
            writer.write("buy," + totalBuy);
            writer.newLine();
            writer.write("result," + result);
        } catch (IOException e) {
            throw new RuntimeException("something went wrong");
        }
    }
}
