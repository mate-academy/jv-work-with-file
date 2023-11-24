package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String OPERATION_SUPPLY = "supply";
    private static final String OPERATION_BUY = "buy";
    private int supplyTotal = 0;
    private int buyTotal = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        if (fromFileName == null || toFileName == null) {
            throw new IllegalArgumentException("File names cannot be null");
        }

        String[] allLines = getAllLines(fromFileName);


        int result = processDataInFile(allLines);


        writeDataToFile(toFileName, this.supplyTotal, this.buyTotal, result);
        this.supplyTotal = 0;
        this.buyTotal = 0;
    }

    private String[] getAllLines(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            return reader.lines().toArray(String[]::new);
        } catch (IOException e) {
            throw new RuntimeException("Error reading file", e);
        }
    }

    private int processDataInFile(String[] file) {
        for (String line : file) {
            String[] fields = line.split(",");
            if (fields.length == 2) {
                String operationType = fields[0].trim();
                int amount = Integer.parseInt(fields[1].trim());

                if (OPERATION_SUPPLY.equals(operationType)) {

                    this.supplyTotal += amount;
                } else if (OPERATION_BUY.equals(operationType)) {
                    this.buyTotal += amount;
                }
            }
        }
        return this.supplyTotal - this.buyTotal;
    }

    private void writeDataToFile(String toFileName, int supplyTotal, int buyTotal, int result) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + supplyTotal);
            writer.newLine();
            writer.write("buy," + buyTotal);
            writer.newLine();
            writer.write("result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file", e);
        }
    }
}
