package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String CSV_SEPARATOR = ",";
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        Map<String, Integer> operationSums = parseFile(fromFileName);
        String report = generateReport(operationSums);
        writeToFile(toFileName, report);
    }

    private Map<String, Integer> parseFile(String fromFileName) {
        Map<String, Integer> operationSums = new LinkedHashMap<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(CSV_SEPARATOR);
                String operation = parts[OPERATION_INDEX].trim();
                int amount = Integer.parseInt(parts[AMOUNT_INDEX].trim());
                operationSums.put(operation, operationSums.getOrDefault(operation, 0) + amount);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fromFileName, e);
        }
        return operationSums;
    }

    private String generateReport(Map<String, Integer> operationSums) {
        int supplySum = operationSums.getOrDefault(SUPPLY, 0);
        int buySum = operationSums.getOrDefault(BUY, 0);
        int result = supplySum - buySum;

        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append(SUPPLY).append(CSV_SEPARATOR)
                .append(supplySum).append(System.lineSeparator());
        reportBuilder.append(BUY).append(CSV_SEPARATOR)
                .append(buySum).append(System.lineSeparator());
        reportBuilder.append(RESULT).append(CSV_SEPARATOR)
                .append(result).append(System.lineSeparator());

        return reportBuilder.toString();
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file: " + toFileName, e);
        }
    }
}

