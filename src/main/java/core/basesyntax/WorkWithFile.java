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

        // Read data from the input file
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String operationType = parts[0];
                int amount = Integer.parseInt(parts[1]);

                if (operationType.equals("supply")) {
                    totalSupply += amount;
                } else if (operationType.equals("buy")) {
                    totalBuy += amount;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int result = totalSupply - totalBuy;

        // Write the result to the output file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + totalSupply + System.lineSeparator());
            writer.write("buy," + totalBuy + System.lineSeparator());
            writer.write("result," + result + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
