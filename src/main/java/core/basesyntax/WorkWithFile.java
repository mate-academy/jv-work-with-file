package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_TYPE = "supply";
    private static final String BUY_TYPE = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        try (
                BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
                BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))
        ) {
            int supplyCount = 0;
            int buyCount = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String operationType = parts[0];
                int amount = Integer.parseInt(parts[1]);
                if (operationType.equals(SUPPLY_TYPE)) {
                    supplyCount += amount;
                } else if (operationType.equals(BUY_TYPE)) {
                    buyCount += amount;
                }
            }
            int result = supplyCount - buyCount;
            writer.write(SUPPLY_TYPE + "," + supplyCount);
            writer.newLine();
            writer.write(BUY_TYPE + "," + buyCount);
            writer.newLine();
            writer.write("result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Error processing files: "
                    + fromFileName + ", "
                    + toFileName, e);
        }
    }
}
