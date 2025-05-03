package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] output = readFromFile(fromFileName);
        String result = calculateReport(output);
        writeToFile(result, toFileName);
    }

    public int[] readFromFile(String fromFileName) {
        int buySum = 0;
        int supplySum = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(SEPARATOR);
                String type = parts[0];
                int amount = Integer.parseInt(parts[1]);

                if (SUPPLY.equals(type)) {
                    supplySum += amount;
                } else if (BUY.equals(type)) {
                    buySum += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
        int supplyResult = supplySum;
        int buyResult = buySum;
        int result = supplySum - buySum;
        return new int[] {supplyResult, buyResult, result};
    }

    public String calculateReport(int[] data) {
        int supplyResult = data[0];
        int buyResult = data[1];
        int result = data[2];
        StringBuilder builder = new StringBuilder();
        builder.append(SUPPLY).append(SEPARATOR).append(supplyResult).append(System.lineSeparator())
                .append(BUY).append(SEPARATOR).append(buyResult).append(System.lineSeparator())
                .append("result,").append(result);
        return builder.toString();
    }

    public void writeToFile(String data, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file " + toFileName, e);
        }
    }
}
