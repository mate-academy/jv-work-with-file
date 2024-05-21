package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";
    private static final String NEW_LINE = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) {
        int totalSupply = 0;
        int totalBuy = 0;

        // Read data from the input file
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(COMMA);
                String operationType = parts[0];
                int amount = Integer.parseInt(parts[1]);

                if (SUPPLY.equals(operationType)) {
                    totalSupply += amount;
                } else if (BUY.equals(operationType)) {
                    totalBuy += amount;
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return;
        }

        int result = totalSupply - totalBuy;

        // Write the result to the output file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(SUPPLY + COMMA + totalSupply + NEW_LINE);
            writer.write(BUY + COMMA + totalBuy + NEW_LINE);
            writer.write(RESULT + COMMA + result + NEW_LINE);
        } catch (IOException e) {
            System.err.println("Error writing the file: " + e.getMessage());
        }
    }
}
