package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR_STRING = ",";
    private static final String SUPPLY_STRING = "supply";
    private static final String BUY_STRING = "buy";
    private static final String RESULT_STRING = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String text = readFile(fromFileName);
        String statistic = getStatisticFromText(text);
        writeStatistic(toFileName, statistic);
    }

    private String readFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder result = new StringBuilder();
            reader.lines().forEach(s -> result.append(s).append(System.lineSeparator()));
            return result.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getStatisticFromText(String text) {
        int supplySum = 0;
        int buySum = 0;
        String[] lines = text.split(System.lineSeparator());
        for (String line : lines) {
            String[] operationInfo = line.split(SEPARATOR_STRING);
            int quantity = Integer.parseInt(operationInfo[1]);
            if (operationInfo[0].equals(SUPPLY_STRING)) {
                supplySum += quantity;
            } else {
                buySum += quantity;
            }
        }
        int difference = supplySum - buySum;
        StringBuilder result = new StringBuilder();
        result.append(SUPPLY_STRING).append(SEPARATOR_STRING).append(supplySum)
                .append(System.lineSeparator()).append(BUY_STRING)
                .append(SEPARATOR_STRING).append(buySum).append(System.lineSeparator())
                .append(RESULT_STRING).append(SEPARATOR_STRING).append(difference);
        return result.toString();
    }

    private void writeStatistic(String toFileName, String statistic) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(statistic);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

