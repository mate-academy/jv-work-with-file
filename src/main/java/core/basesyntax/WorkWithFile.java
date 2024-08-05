package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WorkWithFile {
    private static final String DELIMITER = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String OUTPUT_FILE = "toFileName.csv";

    public static void getStatistic(String fromFileName, String toFileName) {
        Map<String, Integer> operationSums = readFromFile(fromFileName);
        String report = generateReport(operationSums);
        writeToFile(toFileName, report);
    }

    private static Map<String, Integer> readFromFile(String fromFileName) {
        Map<String, Integer> operationSums = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(DELIMITER);
                if (parts.length == 2) {
                    String operation = parts[0].trim();
                    int amount = Integer.parseInt(parts[1].trim());
                    operationSums.put(operation, operationSums.getOrDefault(operation, 0) + amount);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }
        return operationSums;
    }

    private static String generateReport(Map<String, Integer> operationSums) {
        int totalSupply = operationSums.getOrDefault(SUPPLY, 0);
        int totalBuy = operationSums.getOrDefault(BUY, 0);
        int result = totalSupply - totalBuy;

        StringBuilder report = new StringBuilder();
        report.append(SUPPLY).append(DELIMITER).append(totalSupply).append(System.lineSeparator());
        report.append(BUY).append(DELIMITER).append(totalBuy).append(System.lineSeparator());
        report.append(RESULT).append(DELIMITER).append(result).append(System.lineSeparator());

        return report.toString();
    }

    private static void writeToFile(String toFileName, String report) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(toFileName))) {
            bw.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }

    public static void main(String[] args) {
        String[] inputFiles = {"apple.csv", "banana.csv", "grape.csv", "orange.csv"};

        for (String inputFile : inputFiles) {
            getStatistic(inputFile, OUTPUT_FILE);
        }
    }
}
