package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_OPERATION = "supply";
    private static final String BUY_OPEATION = "buy";
    private static final String CSV_SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] amountArray = new int[2];
        fillAmountArray(fromFileName, amountArray);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(SUPPLY_OPERATION + ',' + amountArray[0] + System.lineSeparator());
            writer.write(BUY_OPEATION + ',' + amountArray[1] + System.lineSeparator());
            writer.write("result," + Math.abs(amountArray[0] - amountArray[1]));
        } catch (IOException error) {
            throw new RuntimeException("Can't write data to file "
                    + fromFileName, error);
        }
    }

    private void fillAmountArray(String fromFileName, int[] amountArray) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] content = line.split(CSV_SEPARATOR);
                String operation = content[0];
                int amount = Integer.parseInt(content[1]);
                if (operation.equals(SUPPLY_OPERATION)) {
                    amountArray[0] += amount;
                } else {
                    amountArray[1] += amount;
                }
            }
        } catch (IOException error) {
            throw new RuntimeException("Can't read data from file "
                    + fromFileName, error);
        }
    }
}
