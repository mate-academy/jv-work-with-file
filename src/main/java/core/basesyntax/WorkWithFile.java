package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WorkWithFile {
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
