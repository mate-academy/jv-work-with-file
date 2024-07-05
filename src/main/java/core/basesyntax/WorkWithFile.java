package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String COMMA = ",";
    private static final String RESULT = "result";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final int FIRST_INDEX = 0;
    private static final int SECOND_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String fileContent = readFromFile(fromFileName);
        String report = calculateReport(fileContent);
        writeToFile(report, toFileName);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
        return builder.toString();
    }

    private String calculateReport(String fileData) {
        int supplyTotal = 0;
        int buyTotal = 0;
        int result;
        StringBuilder resultBuilder = new StringBuilder();
        String[] lines = fileData.split(System.lineSeparator());
        for (String line : lines) {
            String[] parts = line.split(COMMA);
            String operationType = parts[FIRST_INDEX].trim();
            int amount = Integer.parseInt(parts[SECOND_INDEX].trim());
            if (SUPPLY.equals(operationType)) {
                supplyTotal += amount;
            } else if (BUY.equals(operationType)) {
                buyTotal += amount;
            }
        }
        result = supplyTotal - buyTotal;
        resultBuilder.append(SUPPLY).append(COMMA).append(supplyTotal).append(LINE_SEPARATOR)
                .append(BUY).append(COMMA).append(buyTotal).append(LINE_SEPARATOR)
                .append(RESULT).append(COMMA).append(result).append(LINE_SEPARATOR);
        return resultBuilder.toString();
    }

    private void writeToFile(String data, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Error writing file: " + toFileName, e);
        }
    }
}
