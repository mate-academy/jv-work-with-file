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
        int supplySum = 0;
        int buySum = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
                BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            String value = reader.readLine();
            while (value != null) {
                String[] parts = value.split(",");
                String operationType = parts[0];
                int amount = Integer.parseInt(parts[1]);
                if (operationType.equals(OPERATION_SUPPLY)) {
                    supplySum += amount;
                } else if (operationType.equals(OPERATION_BUY)) {
                    buySum += amount;
                }
                value = reader.readLine();
            }
            StringBuilder builder = new StringBuilder();
            builder.append(OPERATION_SUPPLY).append(",").append(supplySum)
                    .append(System.lineSeparator()).append(OPERATION_BUY)
                    .append(",").append(buySum).append(System.lineSeparator())
                    .append("result,").append(supplySum - buySum);
            writer.write(builder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Cannot write on file");
        }
    }
}
