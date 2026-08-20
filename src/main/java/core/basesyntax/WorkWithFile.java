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
        String data = readData(fromFileName);
        String report = createReport(data);
        writeData(toFileName, report);
    }

    private String readData(String fromFileName) {
        int supplySum = 0;
        int buySum = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] cells = line.split(",");
                String operationType = cells[0];
                int amount = Integer.parseInt(cells[1]);

                if (operationType.equals(OPERATION_SUPPLY)) {
                    supplySum += amount;
                } else if (operationType.equals(OPERATION_BUY)) {
                    buySum += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }

        return supplySum + "," + buySum;
    }

    private String createReport(String data) {
        if (data == null || data.isEmpty()) {
            return OPERATION_SUPPLY + ",0" + System.lineSeparator()
                    + OPERATION_BUY + ",0" + System.lineSeparator()
                    + "result,0";
        }

        String[] parts = data.split(",");
        int supplySum = Integer.parseInt(parts[0]);
        int buySum = Integer.parseInt(parts[1]);
        int resultSum = supplySum - buySum;

        StringBuilder builder = new StringBuilder();
        builder.append(OPERATION_SUPPLY).append(",")
                .append(supplySum).append(System.lineSeparator())
                .append(OPERATION_BUY).append(",")
                .append(buySum).append(System.lineSeparator())
                .append("result,").append(resultSum);

        return builder.toString();
    }

    private void writeData(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }
}
