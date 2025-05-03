package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int supplyTotal = 0;
        int buyTotal = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String operationType = parts[OPERATION_TYPE_INDEX].trim();
                    int amount = Integer.parseInt(parts[AMOUNT_INDEX].trim());

                    if ("supply".equals(operationType)) {
                        supplyTotal += amount;
                    }

                    if ("buy".equals(operationType)) {
                        buyTotal += amount;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error processing file", e);
        }
        int result = supplyTotal - buyTotal;
        writeReport(toFileName, supplyTotal, buyTotal, result);
    }

    private static void writeReport(String toFileName, int supplyTotal, int buyTotal, int result) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + supplyTotal);
            writer.newLine();
            writer.write("buy," + buyTotal);
            writer.newLine();
            writer.write("result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file " + toFileName, e);
        }
    }
}
