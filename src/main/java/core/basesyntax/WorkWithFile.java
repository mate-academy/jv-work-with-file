package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String OPERATION_SUPPLY = "supply";
    private static final String OPERATION_BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        int supplySum = readFromFile(fromFileName)[0];
        int buySum = readFromFile(fromFileName)[1];
        writeReport(createReport(supplySum, buySum), toFileName);
    }

    private int[] readFromFile(String fromFileName) {
        int supplySum = 0;
        int buySum = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String operationType = parts[0];
                int amount = Integer.parseInt(parts[1]);
                if (operationType.equals(OPERATION_SUPPLY)) {
                    supplySum += amount;
                } else if (operationType.equals(OPERATION_BUY)) {
                    buySum += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("File cannot be found");
        }
        return new int[] {supplySum, buySum};
    }

    private String createReport(int supplySum, int buySum) {
        StringBuilder builder = new StringBuilder();
        builder.append(OPERATION_SUPPLY).append(",").append(supplySum)
                .append(System.lineSeparator())
                .append(OPERATION_BUY).append(",").append(buySum)
                .append(System.lineSeparator())
                .append("result").append(",").append(supplySum - buySum);
        return builder.toString();
    }

    private void writeReport(String data, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write on file");
        }
    }
}
