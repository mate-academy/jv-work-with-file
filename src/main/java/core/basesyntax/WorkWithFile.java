package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA_SEPARATOR = ",";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int totalSupply = 0;
        int totalBuy = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
                BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(COMMA_SEPARATOR);
                String operationType = parts[OPERATION_TYPE_INDEX].trim();
                int amount = Integer.parseInt(parts[AMOUNT_INDEX].trim());

                if (SUPPLY.equals(operationType)) {
                    totalSupply += amount;
                } else if (BUY.equals(operationType)) {
                    totalBuy += amount;
                }
            }

            int result = totalSupply - totalBuy;

            writer.write(SUPPLY + COMMA_SEPARATOR + totalSupply);
            writer.newLine();
            writer.write(BUY + COMMA_SEPARATOR + totalBuy);
            writer.newLine();
            writer.write(RESULT + COMMA_SEPARATOR + result);
            writer.newLine();

        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
