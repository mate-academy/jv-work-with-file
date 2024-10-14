package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        int totalSupply = 0;
        int totalBuy = 0;

        try {
            totalSupply = readData(fromFileName, SUPPLY);
            totalBuy = readData(fromFileName, BUY);
        } catch (IOException e) {
            throw new RuntimeException("Error reading data from file: " + fromFileName, e);
        }

        int result = totalSupply - totalBuy;

        try (FileWriter writer = new FileWriter(toFileName)) {
            writer.write(SUPPLY + "," + totalSupply + System.lineSeparator());
            writer.write(BUY + "," + totalBuy + System.lineSeparator());
            writer.write("result," + result + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Error writing data to file: " + toFileName, e);
        }
    }

    private int readData(String fileName, String operation) throws IOException {
        int total = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (operation.equals(parts[0])) {
                    try {
                        int amount = Integer.parseInt(parts[1]);
                        total += amount;
                    } catch (NumberFormatException e) {
                        throw new IOException("Invalid amount format in file: " + fileName, e);
                    }
                }
            }
        } catch (IOException e) {
            throw new IOException("Error reading file: " + fileName, e);
        }
        return total;
    }
}
