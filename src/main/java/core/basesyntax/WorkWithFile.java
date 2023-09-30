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
        String separator = System.lineSeparator();

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String type = parts[0];
                    int quantity = Integer.parseInt(parts[1]);
                    if ("supply".equals(type)) {
                        supplyTotal += quantity;
                    } else if ("buy".equals(type)) {
                        buyTotal += quantity;
                    }
                }
            }
            int result = supplyTotal - buyTotal;
            StringBuilder builder = new StringBuilder();
            builder.append("supply,").append(supplyTotal).append(separator)
                    .append("buy,").append(buyTotal).append(separator)
                    .append("result,").append(result);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
                writer.write(String.valueOf(builder));
            } catch (IOException e) {
                throw new RuntimeException("Can't read data from the file " + fromFileName, e);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }
}
