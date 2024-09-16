package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    // Define constants
    private static final String COMMA = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        // Read data from file and calculate totals
        int[] totals = calculateTotals(fromFileName);
        int totalSupply = totals[0];
        int totalBuy = totals[1];
        int totalAmount = totalSupply - totalBuy;

        // Write the calculated report into the output file
        writeReport(toFileName, totalSupply, totalBuy, totalAmount);
    }

    private int[] calculateTotals(String fromFileName) {
        int totalSupply = 0;
        int totalBuy = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(COMMA);
                String operationType = data[0];
                int amount = Integer.parseInt(data[1]);

                if (operationType.equals(SUPPLY)) {
                    totalSupply += amount;
                } else if (operationType.equals(BUY)) {
                    totalBuy += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + fromFileName, e);
        }

        return new int[] {totalSupply, totalBuy};
    }

    private void writeReport(String toFileName, int totalSupply, int totalBuy, int totalAmount) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(SUPPLY + COMMA + totalSupply + System.lineSeparator());
            writer.write(BUY + COMMA + totalBuy + System.lineSeparator());
            writer.write(RESULT + COMMA + totalAmount + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Error writing file: " + toFileName, e);
        }
    }
}
