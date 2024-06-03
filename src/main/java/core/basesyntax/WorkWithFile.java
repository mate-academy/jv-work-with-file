package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String COMMA_DELIMITER = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int supplyTotal = 0;
        int buyTotal = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(new File(fromFileName)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(COMMA_DELIMITER);
                String operation = parts[OPERATION_INDEX].trim();
                int amount = Integer.parseInt(parts[AMOUNT_INDEX].trim());

                if (SUPPLY.equals(operation)) {
                    supplyTotal += amount;
                } else if (BUY.equals(operation)) {
                    buyTotal += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("File processing error", e);
        }

        int result = supplyTotal - buyTotal;
        writeFile(toFileName, supplyTotal, buyTotal, result);
    }

    private void writeFile(String toFileName, int supplyTotal, int buyTotal, int result) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(SUPPLY + COMMA_DELIMITER + supplyTotal);
            writer.newLine();
            writer.write(BUY + COMMA_DELIMITER + buyTotal);
            writer.newLine();
            writer.write("result" + COMMA_DELIMITER + result);
        } catch (IOException e) {
            throw new RuntimeException("File writing error", e);
        }
    }
}
