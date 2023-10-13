package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMMA_SEPARATOR = ",";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFile(fromFileName);
        String report = createReport(fromFileName);
        writeToFile(report, toFileName);
    }

    private String readFile(String fileName) {
        StringBuilder content = new StringBuilder();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading the file: " + fileName, e);
        }
        
        return content.toString();
    }

    private String createReport(String fileName) {
        int supplyTotal = 0;
        int buyTotal = 0;
        StringBuilder reportBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(COMMA_SEPARATOR);
                if (parts.length == 2) {
                    String operationType = parts[OPERATION_TYPE_INDEX];
                    int amount = Integer.parseInt(parts[AMOUNT_INDEX]);
                    if ("supply".equals(operationType)) {
                        supplyTotal += amount;
                    } else if ("buy".equals(operationType)) {
                        buyTotal += amount;
                    }
                }
            }

            int result = supplyTotal - buyTotal;
            reportBuilder.append("supply,").append(supplyTotal).append(System.lineSeparator());
            reportBuilder.append("buy,").append(buyTotal).append(System.lineSeparator());
            reportBuilder.append("result,").append(result);
        } catch (IOException e) {
            throw new RuntimeException("Error reading the file: " + fileName, e);
        }

        return reportBuilder.toString();
    }

    private void writeToFile(String data, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Error writing to the file: " + fileName, e);
        }
    }
}
