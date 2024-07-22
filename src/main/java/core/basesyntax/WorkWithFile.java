package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WorkWithFile {
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        Map<String, Integer> textFromFile = readFromFile(fromFileName);
        String report = generateReport(textFromFile);
        writeToFile(report, toFileName);
    }

    private Map<String, Integer> readFromFile(String fromFileName) {
        Map<String, Integer> productQuantities = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(COMMA);
                String product = parts[0];
                int quantity = Integer.parseInt(parts[1]);

                productQuantities.put(product, productQuantities.getOrDefault(
                        product, 0) + quantity);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }

        return productQuantities;
    }

    private String generateReport(Map<String, Integer> productQuantities) {
        StringBuilder report = new StringBuilder();
        int supplySum = productQuantities.getOrDefault("supply", 0);
        int buySum = productQuantities.getOrDefault("buy", 0);
        int result = supplySum - buySum;

        report.append("supply,").append(supplySum).append(System.lineSeparator());
        report.append("buy,").append(buySum).append(System.lineSeparator());
        report.append("result,").append(result);

        return report.toString();
    }

    private void writeToFile(String text, String toFile) {

        try (FileWriter file = new FileWriter(toFile);
                BufferedWriter writer = new BufferedWriter(file)) {
            writer.write(text);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
