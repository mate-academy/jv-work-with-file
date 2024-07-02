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
                String[] parts = line.split(",");
                String operation = parts[0];
                int amount = Integer.parseInt(parts[1]);
                if ("supply".equals(operation)) {
                    totalSupply += amount;
                } else if ("buy".equals(operation)) {
                    totalBuy += amount;
                }

            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from this file", e);
        }
        int result = totalSupply - totalBuy;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName,false))) {
            writer.write("supply," + totalSupply);
            writer.newLine();
            writer.write("buy," + totalBuy);
            writer.newLine();
            writer.write("result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
