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

        // Read file and calculate totals
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String action = parts[0].trim();
                int amount = Integer.parseInt(parts[1].trim());

                if ("supply".equals(action)) {
                    totalSupply += amount;
                } else if ("buy".equals(action)) {
                    totalBuy += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file: " + fromFileName, e);
        }

        int result = totalSupply - totalBuy;

        // Build the final report
        String report = new StringBuilder()
                .append("supply,").append(totalSupply).append(System.lineSeparator())
                .append("buy,").append(totalBuy).append(System.lineSeparator())
                .append("result,").append(result)
                .toString();

        // Write the result to output file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write to file: " + toFileName, e);
        }
    }
}
