package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String lineSeparator = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) {
        int supplyTotal = 0;
        int buyTotal = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String operationType = parts[0];
                int amount = Integer.parseInt(parts[1].trim());
                if (operationType.equals("supply")) {
                    supplyTotal += amount;
                } else {
                    buyTotal += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading data from the file " + fromFileName, e);
        }
        int result = supplyTotal - buyTotal;
        writeReport(toFileName, supplyTotal, buyTotal, result);
    }

    private static void writeReport(String toFileName, int supplyTotal, int buyTotal, int result) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + supplyTotal + lineSeparator + "buy," + buyTotal + lineSeparator
                    + "result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Error writing report to the file " + toFileName, e);
        }
    }
}
