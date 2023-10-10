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
        String[] dataFromFile = readData(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(report, toFileName);
    }

    private String[] readData(String fileName) {
        int supplyTotal = 0;
        int buyTotal = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
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
        } catch (IOException e) {
            throw new RuntimeException("Error reading the file: " + fileName, e);
        }

        int result = supplyTotal - buyTotal;
        return new String[]{
                "supply," + supplyTotal,
                "buy," + buyTotal,
                "result," + result
        };
    }

    private String createReport(String[] data) {
        return String.join(System.lineSeparator(), data);
    }

    private void writeToFile(String data, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Error writing to the file: " + fileName, e);
        }
    }
}
