package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class WorkWithFile {
    private static final String OPERATION_SUPPLY = "supply";
    private static final String OPERATION_BUY = "buy";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
                BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {

            Map<String, Integer> supplyData = new LinkedHashMap<>();
            Map<String, Integer> buyData = new LinkedHashMap<>();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String operationType = data[OPERATION_TYPE_INDEX];
                int amount = Integer.parseInt(data[AMOUNT_INDEX]);
                updateOperationData(operationType, amount, supplyData, buyData);
            }

            int supplyResult = calculateTotal(supplyData);
            int buyResult = calculateTotal(buyData);

            writeDataToOutput(supplyData, writer);
            writeDataToOutput(buyData, writer);

            writer.write("result," + (supplyResult - buyResult) + System.lineSeparator());
            System.out.println("Report generated successfully.");

        } catch (IOException e) {
            throw new RuntimeException("Error while working with the file: "
                                                                        + e.getMessage(), e);
        }
    }

    private void updateOperationData(String operationType,
                                     int amount, Map<String,
                                     Integer> supplyData,
                                     Map<String,
                                     Integer> buyData) {
        if (OPERATION_SUPPLY.equals(operationType)) {
            supplyData.put(operationType, supplyData.getOrDefault(operationType, 0) + amount);
        } else if (OPERATION_BUY.equals(operationType)) {
            buyData.put(operationType, buyData.getOrDefault(operationType, 0) + amount);
        }
    }

    private int calculateTotal(Map<String, Integer> data) {
        int total = 0;
        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            total += entry.getValue();
        }
        return total;
    }

    private void writeDataToOutput(Map<String, Integer> data, BufferedWriter writer)
                                                              throws IOException {
        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            String operationType = entry.getKey();
            int sum = entry.getValue();
            writer.write(operationType + "," + sum + System.lineSeparator());
        }
    }
}
