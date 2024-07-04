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

    public void getStatistic(String fromFileName, String toFileName) {
        int supplyTotal = 0;
        int buyTotal = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                String[] parts = value.split(COMMA);
                String operationType = parts[0].trim();
                int amount = Integer.parseInt(parts[1].trim());
                if (SUPPLY.equals(operationType)) {
                    supplyTotal += amount;
                } else if (BUY.equals(operationType)) {
                    buyTotal += amount;
                }
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + fromFileName, e);
        }
        int result = supplyTotal - buyTotal;
        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append(SUPPLY).append(COMMA).append(supplyTotal).append(LINE_SEPARATOR);
        resultBuilder.append(BUY).append(COMMA).append(buyTotal).append(LINE_SEPARATOR);
        resultBuilder.append(RESULT).append(COMMA).append(result).append(LINE_SEPARATOR);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(resultBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Error writing file: " + toFileName, e);
        }
    }
}
