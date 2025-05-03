package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String delimiter = ",";
        int totalSupply = 0;
        int totalBuy = 0;

        // reading + calc
        try (BufferedReader br = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(delimiter);
                String type = values[0].trim();
                int quantity = Integer.parseInt(values[1].trim());

                // calc supply & buy
                if ("supply".equalsIgnoreCase(type)) {
                    totalSupply += quantity;
                } else if ("buy".equalsIgnoreCase(type)) {
                    totalBuy += quantity;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: "
                    + fromFileName + " - " + e.getMessage(), e);
        }

        // subtract
        int result = totalSupply - totalBuy;

        // writing result
        try (FileWriter writer = new FileWriter(toFileName)) {
            writer.write("supply," + totalSupply + System.lineSeparator());
            writer.write("buy," + totalBuy + System.lineSeparator());
            writer.write("result," + result + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file: "
                    + toFileName + " - " + e.getMessage(), e);
        }
    }
}
