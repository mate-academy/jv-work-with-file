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
        String result = processDataInFile(allLines);
        writeDataToFile(toFileName, result);
    }

    private String[] getAllLines(String fromFileName) {
        if (fromFileName == null) {
            throw new IllegalArgumentException("File name cannot be null!");
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            return reader.lines().toArray(String[]::new);
        } catch (IOException e) {
            throw new RuntimeException("Error reading file" + fromFileName, e);
        }
    }

    private String processDataInFile(String[] lines) {
        int supplyTotal = 0;
        int buyTotal = 0;
        for (String line : lines) {
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
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(OPERATION_SUPPLY).append(SEPARATOR).append(supplyTotal)
                .append(System.lineSeparator()).append(OPERATION_BUY).append(SEPARATOR)
                .append(buyTotal).append(System.lineSeparator())
                .append("result").append(SEPARATOR).append(supplyTotal - buyTotal);
        return stringBuilder.toString();
    }

    private void writeDataToFile(String toFileName, String result) {
        if (toFileName == null) {
            throw new IllegalArgumentException("File name cannot be null ");
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file " + toFileName, e);
        }
    }
}
