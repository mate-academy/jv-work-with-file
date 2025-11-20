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

    public void getStatistic(String fromFileName, String toFileName) {
        Map<String, Integer> reportData = readData(fromFileName);
        String report = calculateReport(reportData);
        writeReport(toFileName, report);
    }

    private Map<String, Integer> readData(String fileName) {
        Map<String, Integer> data = new HashMap<>();
        data.put(SUPPLY, 0);
        data.put(BUY, 0);

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 2) {
                    continue;
                }
                String type = parts[0];
                int amount;
                try {
                    amount = Integer.parseInt(parts[1]);
                } catch (NumberFormatException e) {
                    continue;
                }

                if (SUPPLY.equals(type)) {
                    data.put(SUPPLY, data.get(SUPPLY) + amount);
                } else if (BUY.equals(type)) {
                    data.put(BUY, data.get(BUY) + amount);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file: " + fileName, e);
        }

        return data;
    }

    private String calculateReport(Map<String, Integer> data) {
        int supplyTotal = data.get(SUPPLY);
        int buyTotal = data.get(BUY);
        int result = supplyTotal - buyTotal;

        StringBuilder report = new StringBuilder();
        report.append(SUPPLY).append(",").append(supplyTotal).append("\n");
        report.append(BUY).append(",").append(buyTotal).append("\n");
        report.append("result").append(",").append(result).append("\n");

        return report.toString();
    }

    private void writeReport(String fileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write file: " + fileName, e);
        }
    }
}
