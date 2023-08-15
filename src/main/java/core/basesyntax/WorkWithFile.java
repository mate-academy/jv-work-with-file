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

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String operation = parts[0].trim();
                    int amount = Integer.parseInt(parts[1].trim());

                    if (operation.equals("supply")) {
                        supplyTotal += amount;
                    } else if (operation.equals("buy")) {
                        buyTotal += amount;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error occured while reading data from: " + fromFileName, e);
        } finally {
            writeReport(toFileName, supplyTotal, buyTotal);
        }
    }

    private void writeReport(String toFileName, int supplyTotal, int buyTotal) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + supplyTotal + System.lineSeparator());
            writer.write("buy," + buyTotal + System.lineSeparator());
            writer.write("result," + (supplyTotal - buyTotal));
        } catch (IOException e) {
            throw new RuntimeException("Error occured while writing data to: " + toFileName, e);
        }
    }
}
