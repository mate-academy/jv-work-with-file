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
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
                BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            int[] totals = new int[2]; // totals[0] - supplyTotal, totals[1] - buyTotal

            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                String operationType = fields[0];
                int amount = Integer.parseInt(fields[1]);
                updateTotals(operationType, amount, totals);
            }

            int result = calculateResult(totals[0], totals[1]);
            writeStatistics(writer, totals[0], totals[1], result);
        } catch (IOException e) {
            throw new RuntimeException("Error occurred: " + e.getMessage(), e);
        }
    }

    private void updateTotals(String operationType, int amount, int[] totals) {
        if (operationType.equals(OPERATION_SUPPLY)) {
            totals[0] += amount;
        } else if (operationType.equals(OPERATION_BUY)) {
            totals[1] += amount;
        }
    }

    private int calculateResult(int supplyTotal, int buyTotal) {
        return supplyTotal - buyTotal;
    }

    private void writeStatistics(BufferedWriter writer, int supplyTotal, int buyTotal, int result)
            throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("supply,").append(supplyTotal).append(System.lineSeparator());
        sb.append("buy,").append(buyTotal).append(System.lineSeparator());
        sb.append("result,").append(result).append(System.lineSeparator());
        writer.write(sb.toString());
    }
}
