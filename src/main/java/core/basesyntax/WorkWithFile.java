package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String COMMA = ",";
    private static final String RESULT = "result";
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final int DEFAULT_AMOUNT = 0;
    private static final int REQUIRED_PARTS_COUNT = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        Map<String, Integer> operationTotals = readData(fromFileName);
        int supplyTotal = operationTotals.getOrDefault(SUPPLY, DEFAULT_AMOUNT);
        int buyTotal = operationTotals.getOrDefault(BUY, DEFAULT_AMOUNT);
        int result = supplyTotal - buyTotal;
        writeReport(toFileName, supplyTotal, buyTotal, result);
    }

    private Map<String, Integer> readData(String fileName) {
        Map<String, Integer> operationTotals = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(COMMA);
                if (parts.length == REQUIRED_PARTS_COUNT) {
                    String operation = parts[OPERATION_INDEX].trim();
                    try {
                        int amount = Integer.parseInt(parts[AMOUNT_INDEX].trim());
                        operationTotals.put(operation, operationTotals
                                .getOrDefault(operation, DEFAULT_AMOUNT) + amount);
                    } catch (NumberFormatException e) {
                        throw new RuntimeException("Error parsing amount in line: " + line, e);
                    }
                } else {
                    System.err.println("Invalid line format: " + line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fileName, e);
        }
        return operationTotals;
    }

    private void writeReport(String fileName, int supplyTotal, int buyTotal, int result) {
        StringBuilder reportData = new StringBuilder();
        reportData.append(SUPPLY).append(COMMA).append(supplyTotal).append(System.lineSeparator());
        reportData.append(BUY).append(COMMA).append(buyTotal).append(System.lineSeparator());
        reportData.append(RESULT).append(COMMA).append(result);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(reportData.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + fileName, e);
        }
    }
}
