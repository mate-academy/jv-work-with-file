package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFromFile(fromFileName);
        String report = createReport(data);
        writeToFile(toFileName, report);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder dataBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            final int operation_type_index = 0;
            final int amount_index = 1;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String operationType = parts[operation_type_index].trim();
                    int amount = Integer.parseInt(parts[amount_index].trim());
                    dataBuilder.append(operationType).append(",")
                            .append(amount).append(System.lineSeparator());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading data from file "
                    + fromFileName, e);
        }
        return dataBuilder.toString();
    }

    private String createReport(String data) {
        Map<String, Integer> operationMap = new HashMap<>();
        String[] lines = data.split(System.lineSeparator());

        for (String line : lines) {
            String[] parts = line.split(",");
            String operationType = parts[0];
            int amount = Integer.parseInt(parts[1]);
            operationMap.put(operationType, operationMap.getOrDefault(operationType,
                    0) + amount);
        }

        int supply = operationMap.getOrDefault("supply", 0);
        int buy = operationMap.getOrDefault("buy", 0);
        int result = supply - buy;

        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("supply,").append(supply).append(System.lineSeparator());
        reportBuilder.append("buy,").append(buy).append(System.lineSeparator());
        reportBuilder.append("result,").append(result);
        return reportBuilder.toString();
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Error writing data to file " + toFileName, e);
        }
    }
}
