package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            int totalSupply = 0;
            int totalBuy = 0;
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 2) {
                    String operationType = data[0].trim();
                    int amount = Integer.parseInt(data[1].trim());
                    if (operationType.equals("supply")) {
                        totalSupply += amount;
                    } else if (operationType.equals("buy")) {
                        totalBuy += amount;
                    }
                }
            }
            reader.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName));
            writer.write("supply," + totalSupply + "\n");
            writer.write("buy," + totalBuy + "\n");
            writer.write("result," + (totalSupply - totalBuy) + "\n");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't read or write the file", e);
        }
    }
}
