package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_TYPE = "supply";
    private static final String BUY_TYPE = "buy";
    private static final int TYPE_INDEX = 0;
    private static final int QUANTITY_INDEX = 1;
    private static final String SEPARATOR = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFromFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(report, toFileName);
    }

    private String readFromFile(String fileName) {
        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent.append(line).append(SEPARATOR);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fileName, e);
        }
        return fileContent.toString();
    }

    private String createReport(String fileData) {
        int supplyTotal = 0;
        int buyTotal = 0;
        String[] lines = fileData.split(SEPARATOR);

        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length == 2) {
                String type = parts[TYPE_INDEX];
                int quantity = Integer.parseInt(parts[QUANTITY_INDEX]);
                if (SUPPLY_TYPE.equals(type)) {
                    supplyTotal += quantity;
                } else if (BUY_TYPE.equals(type)) {
                    buyTotal += quantity;
                }
            }
        }

        int result = supplyTotal - buyTotal;
        StringBuilder builder = new StringBuilder();
        builder.append("supply,").append(supplyTotal).append(SEPARATOR)
                .append("buy,").append(buyTotal).append(SEPARATOR)
                .append("result,").append(result);

        return builder.toString();
    }

    private void writeToFile(String report, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + fileName, e);
        }
    }
}
