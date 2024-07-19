package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WorkWithFile {
    /*
    public static void getStatistic(String fromFileName, String toFileName) {
        Map<String, Integer> operationSums = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String operation = parts[0].trim();
                    int amount = Integer.parseInt(parts[1].trim());

                    operationSums.put(operation, operationSums.getOrDefault(operation, 0) + amount);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }

        int totalSupply = operationSums.getOrDefault("supply", 0);
        int totalBuy = operationSums.getOrDefault("buy", 0);
        int result = totalSupply - totalBuy;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(toFileName))) {
            bw.write("supply," + totalSupply);
            bw.newLine();
            bw.write("buy," + totalBuy);
            bw.newLine();
            bw.write("result," + result);
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }

    public static void main(String[] args) {
        String[] inputFiles = {"apple.csv", "banana.csv", "grape.csv", "orange.csv"};
        String outputFile = "toFileName.csv";

        for (String inputFile : inputFiles) {
            getStatistic(inputFile, outputFile);
        }
    }
}

     */
    private static final String DELIMITER = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String OUTPUT_FILE = "toFileName.csv";

    // Original method name to match the test cases
    public static void getStatistic(String fromFileName, String toFileName) {
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

        int totalSupply = operationSums.getOrDefault(SUPPLY, 0);
        int totalBuy = operationSums.getOrDefault(BUY, 0);
        int result = totalSupply - totalBuy;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(toFileName))) {
            bw.write(SUPPLY + DELIMITER + totalSupply);
            bw.newLine();
            bw.write(BUY + DELIMITER + totalBuy);
            bw.newLine();
            bw.write(RESULT + DELIMITER + result);
            bw.newLine();
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
