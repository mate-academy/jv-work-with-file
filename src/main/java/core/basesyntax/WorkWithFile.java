package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        int supplySum = 0;
        int buySum = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                String operationType = details[0];
                int amount = Integer.parseInt(details[1]);

                if (operationType.equals(SUPPLY)) {
                    supplySum += amount;
                } else if (operationType.equals(BUY)) {
                    buySum += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while reading file " + fromFileName, e);
        }

        writeReport(toFileName, supplySum, buySum);
    }

    private void writeReport(String toFileName, int supplySum, int buySum) {
        int result = supplySum - buySum;
        StringBuilder report = new StringBuilder();
        report.append(SUPPLY).append(",").append(supplySum).append(System.lineSeparator())
                .append(BUY).append(",").append(buySum).append(System.lineSeparator())
                .append("result,").append(result);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report.toString());
        } catch (IOException e) {
            throw new RuntimeException("Error while writing file " + toFileName, e);
        }
    }
}
