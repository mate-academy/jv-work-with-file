package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String COMMA = ",";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] reportData = readFromFile(fromFileName);
        String report = calculateReport(reportData[SUPPLY_INDEX], reportData[BUY_INDEX]);
        writeIntoFile(toFileName, report);
    }

    private int[] readFromFile(String fromFileName) {
        int supplySum = 0;
        int buySum = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(COMMA);
                String operationType = details[SUPPLY_INDEX];
                int amount = Integer.parseInt(details[BUY_INDEX]);

                if (operationType.equals(SUPPLY)) {
                    supplySum += amount;
                } else if (operationType.equals(BUY)) {
                    buySum += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while reading file " + fromFileName, e);
        }

        return new int[]{supplySum, buySum};
    }

    private String calculateReport(int supplySum, int buySum) {
        int result = supplySum - buySum;

        return new StringBuilder().append(SUPPLY).append(COMMA).append(supplySum)
                .append(System.lineSeparator()).append(BUY).append(COMMA).append(buySum)
                .append(System.lineSeparator()).append(RESULT).append(COMMA)
                .append(result).toString();
    }

    private void writeIntoFile(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Error while writing file " + toFileName, e);
        }
    }
}
