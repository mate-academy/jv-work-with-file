package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String OPERATION_SUPPLY = "supply";
    private static final String OPERATION_BUY = "buy";
    private static final String SEPARATOR = ",";
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] allLines = getAllLines(fromFileName);
        int[] result = processDataInFile(allLines);
        writeDataToFile(toFileName, result);
    }

    private String[] getAllLines(String fromFileName) {
        if (fromFileName == null) {
            throw new IllegalArgumentException("File name cannot be null " + fromFileName);
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            return reader.lines().toArray(String[]::new);
        } catch (IOException e) {
            throw new RuntimeException("Error reading file" + fromFileName, e);
        }
    }

    private int[] processDataInFile(String[] file) {
        int supplyTotal = 0;
        int buyTotal = 0;
        for (String line : file) {
            String[] fields = line.split(SEPARATOR);
            if (fields.length == 2) {
                String operationType = fields[OPERATION_INDEX].trim();
                int amount = Integer.parseInt(fields[AMOUNT_INDEX].trim());

                if (OPERATION_SUPPLY.equals(operationType)) {
                    supplyTotal += amount;
                } else if (OPERATION_BUY.equals(operationType)) {
                    buyTotal += amount;
                }
            }
        }
        return new int[]{supplyTotal, buyTotal, supplyTotal - buyTotal};
    }

    private void writeDataToFile(String toFileName, int[] result) {
        if (toFileName == null) {
            throw new IllegalArgumentException("File name cannot be null " + toFileName);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            String output = "supply," + result[0] + System.lineSeparator()
                    + "buy," + result[1] + System.lineSeparator() + "result," + result[2];
            writer.write(output);
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file " + toFileName, e);
        }
    }
}
