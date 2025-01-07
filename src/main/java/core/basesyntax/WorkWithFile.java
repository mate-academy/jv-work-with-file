package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int totalSupply = 0;
        int totalBuy = 0;

        // Reading data from the input file
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 2) {
                    throw new IllegalArgumentException("Malformed line: '" + line
                            + "'. Expected format: 'operation,amount'");
                }
                String operation = parts[0].trim();
                try {
                    int amount = Integer.parseInt(parts[1].trim());
                    if ("supply".equals(operation)) {
                        totalSupply += amount;
                    } else if ("buy".equals(operation)) {
                        totalBuy += amount;
                    } else {
                        throw new IllegalArgumentException("Unknown operation: '"
                                + operation + "' in line: '" + line + "'");
                    }
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid number format for amount in line: '"
                            + line + "'", e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: '" + fromFileName
                    + "'. Ensure the file exists and is readable.", e);
        }

        // Creating the report
        List<String> report = new ArrayList<>();
        report.add("supply," + totalSupply);
        report.add("buy," + totalBuy);
        report.add("result," + (totalSupply - totalBuy));

        // Writing the report to the output file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            for (String line : report) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file: '"
                    + toFileName + "'. Ensure the file is writable.", e);
        }
    }
}
