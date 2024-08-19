package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {

        int[] totals = calculateStatistics(fromFileName);
        int totalSupply = totals[0];
        int totalBuy = totals[1];
        int result = totalSupply - totalBuy;

        writeStatistics(toFileName, totalSupply, totalBuy, result);
    }

    private int[] calculateStatistics(String fromFileName) {
        int totalSupply = 0;
        int totalBuy = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 2) {
                    continue;
                }
                String operation = parts[0].trim();
                int amount = Integer.parseInt(parts[1].trim());

                if (operation.equals(SUPPLY)) {
                    totalSupply += amount;
                } else if (operation.equals(BUY)) {
                    totalBuy += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to correctly read data from the file "
                    + fromFileName, e);
        }

        return new int[]{totalSupply, totalBuy};
    }

    private void writeStatistics(String toFileName, int totalSupply, int totalBuy, int result) {

        StringBuilder output = new StringBuilder();
        output.append(SUPPLY).append(",").append(totalSupply).append(System.lineSeparator());
        output.append(BUY).append(",").append(totalBuy).append(System.lineSeparator());
        output.append("result,").append(result).append(System.lineSeparator());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(output.toString());
        } catch (IOException e) {
            throw new RuntimeException("Failed to correctly write data to the file "
                    + toFileName, e);
        }
    }
}
