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
            int totalSupply = 0;
            int totalBuy = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    int amount = Integer.parseInt(parts[1].trim());
                    if (parts[0].trim().equals("supply")) {
                        totalSupply += amount;
                    } else if (parts[0].trim().equals("buy")) {
                        totalBuy += amount;
                    }
                }
            }
            int result = totalSupply - totalBuy;
            writer.write("supply," + totalSupply);
            writer.newLine();
            writer.write("buy," + totalBuy);
            writer.newLine();
            writer.write("result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Error reading or writing the file: ", e);
        }
    }
}
