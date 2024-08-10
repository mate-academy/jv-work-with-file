package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int ZERO_INITIALIZE = 0;
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;
    private static final int RESULT_INDEX = 2;
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String SUPPLY_LITERAL = "supply";
    private static final String BUY_LITERAL = "buy";
    private static final String RESULT_LITERAL = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readFromFile(fromFileName);
        int[] results = processStatistics(data);
        writeToFile(toFileName, results[SUPPLY_INDEX], results[BUY_INDEX], results[RESULT_INDEX]);
    }

    private String[] readFromFile(String fromFileName) {
        StringBuilder data = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading from " + fromFileName, e);
        }
        return data.toString().split(System.lineSeparator());
    }

    private int[] processStatistics(String[] data) {
        int totalSupply = ZERO_INITIALIZE;
        int totalBuy = ZERO_INITIALIZE;

        for (String line : data) {
            String[] parts = line.split(",");
            String operationType = parts[OPERATION_INDEX];
            int amount = Integer.parseInt(parts[AMOUNT_INDEX]);

            if (operationType.equals(SUPPLY_LITERAL)) {
                totalSupply += amount;
            } else if (operationType.equals(BUY_LITERAL)) {
                totalBuy += amount;
            }
        }

        int result = totalSupply - totalBuy;
        return new int[]{totalSupply, totalBuy, result};
    }

    private void writeToFile(String toFileName, int totalSupply, int totalBuy, int result) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(SUPPLY_LITERAL + "," + totalSupply + System.lineSeparator());
            writer.write(BUY_LITERAL + "," + totalBuy + System.lineSeparator());
            writer.write(RESULT_LITERAL + "," + result + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file " + toFileName);
        }
    }
}
