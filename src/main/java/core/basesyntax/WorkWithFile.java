package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap; // H перед M
import java.util.List;
import java.util.Map;

public class WorkWithFile {
    private static final String OPERATION_SUPPLY = "supply";
    private static final String OPERATION_BUY = "buy";
    private static final String OPERATION_RESULT = "result"; // ВИПРАВЛЕНО
    private static final String DELIMITER = ",";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {

        List<String> lines = readFromFile(fromFileName);
        Map<String, Integer> operationSums = processData(lines);

        String report = createReport(operationSums);

        writeToFile(report, toFileName);
    }

    private List<String> readFromFile(String fileName) {
        try {
            Path fromFilePath = Paths.get(fileName);
            return Files.readAllLines(fromFilePath);
        } catch (IOException e) {
            throw new RuntimeException("Error reading data from file: " + fileName, e);
        }
    }

    private Map<String, Integer> processData(List<String> lines) {
        Map<String, Integer> operationSums = new HashMap<>();
        operationSums.put(OPERATION_SUPPLY, 0);
        operationSums.put(OPERATION_BUY, 0);

        for (String line : lines) {
            if (line.trim().isEmpty()) {
                continue;
            }

            String[] parts = line.split(DELIMITER);

            if (parts.length < 2) {
                continue;
            }

            String operationType = parts[OPERATION_TYPE_INDEX].trim();
            int amount;

            try {
                amount = Integer.parseInt(parts[AMOUNT_INDEX].trim());
            } catch (NumberFormatException e) {
                continue;
            }

            if (operationType.equals(OPERATION_SUPPLY) || operationType.equals(OPERATION_BUY)) {
                int currentSum = operationSums.get(operationType);
                operationSums.put(operationType, currentSum + amount);
            }
        }
        return operationSums;
    }

    private String createReport(Map<String, Integer> operationSums) {
        int totalSupply = operationSums.getOrDefault(OPERATION_SUPPLY, 0);
        int totalBuy = operationSums.getOrDefault(OPERATION_BUY, 0);
        int result = totalSupply - totalBuy;

        return OPERATION_SUPPLY + DELIMITER + totalSupply + "\n"
                + OPERATION_BUY + DELIMITER + totalBuy + "\n"
                + OPERATION_RESULT + DELIMITER + result + "\n";
    }

    private void writeToFile(String data, String fileName) {
        try {
            Path toFilePath = Paths.get(fileName);
            Files.writeString(toFilePath, data);
        } catch (IOException e) {
            throw new RuntimeException("Error writing data to file: " + fileName, e);
        }
    }
}
