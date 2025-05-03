package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> data = readDataFromFile(fromFileName);
        List<String> processedData = processStatistic(data);
        writeDataToFile(processedData, toFileName);
    }

    private List<String> readDataFromFile(String fileName) {
        List<String> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading data from the file: " + fileName, e);
        }
        return data;
    }

    private List<String> processStatistic(List<String> data) {
        int supplyTotal = 0;
        int buyTotal = 0;
        for (String entry : data) {
            String[] entryParts = entry.split(SEPARATOR);
            String operationType = entryParts[OPERATION_INDEX].trim();
            int amount = Integer.parseInt(entryParts[AMOUNT_INDEX].trim());
            if (operationType.equals("supply")) {
                supplyTotal += amount;
            } else if (operationType.equals("buy")) {
                buyTotal += amount;
            }
        }
        int result = supplyTotal - buyTotal;
        List<String> processedData = new ArrayList<>();
        processedData.add("supply," + supplyTotal);
        processedData.add("buy," + buyTotal);
        processedData.add("result," + result);
        return processedData;
    }

    private void writeDataToFile(List<String> processedData, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String line : processedData) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error writing data to the file: " + fileName, e);
        }
    }
}
