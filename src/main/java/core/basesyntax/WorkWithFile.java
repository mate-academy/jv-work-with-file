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
    private static final int INITIAL_SUM = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        int[] supplyAndBuySums = readFromFile(fromFileName);
        int supplySum = supplyAndBuySums[0];
        int buySum = supplyAndBuySums[1];

        String report = generateReport(supplySum, buySum);

        writeIntoFile(toFileName, report);
    }

    public int[] readFromFile(String fromFileName) {
        int supplySum = INITIAL_SUM;
        int buySum = INITIAL_SUM;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(COMMA);
                String operationType = parts[0];
                int amount = Integer.parseInt(parts[1]);

                if (operationType.equals(SUPPLY)) {
                    supplySum += amount;
                } else if (operationType.equals(BUY)) {
                    buySum += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file", e);
        }

        return new int[] {supplySum, buySum};
    }

    public String generateReport(int supplySum, int buySum) {
        int result = supplySum - buySum;

        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append(SUPPLY).append(COMMA).append(supplySum).append(System.lineSeparator())
                .append(BUY).append(COMMA).append(buySum).append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(result).append(System.lineSeparator());

        return reportBuilder.toString();
    }

    public void writeIntoFile(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
