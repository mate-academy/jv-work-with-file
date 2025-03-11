package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WorkWithFile {
    private static final Logger LOGGER = Logger.getLogger(WorkWithFile.class.getName());
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String type = parts[0];
                int amount = parseAmount(parts[1]);

                if (SUPPLY.equals(type)) {
                    supply += amount;
                } else if (BUY.equals(type)) {
                    buy += amount;
                }
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error reading the file: " + fromFileName, e);
        }

        int result = calculateResult(supply, buy);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writeResult(writer, supply, buy, result);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error writing to the file: " + toFileName, e);
        }
    }

    private int parseAmount(String amountStr) {
        try {
            return Integer.parseInt(amountStr);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.WARNING, "Unable to parse amount: " + amountStr, e);
            return 0;
        }
    }

    private int calculateResult(int supply, int buy) {
        return supply - buy;
    }

    private void writeResult(BufferedWriter writer, int supply, int buy, int result)
            throws IOException {
        writer.write(SUPPLY + "," + supply + System.lineSeparator());
        writer.write(BUY + "," + buy + System.lineSeparator());
        writer.write("result," + result + System.lineSeparator());
    }
}
