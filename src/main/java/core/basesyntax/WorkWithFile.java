package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    static final String DELIMITER = ",";
    static final String OPPERATION_SUPPLY = "supply";
    static final String OPPERATION_BUY = "buy";
    static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))
             ; BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {

            int supplyTotal = 0;
            int buyTotal = 0;
            String line;

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(DELIMITER);
                String operationType = values[0];
                int amount = Integer.parseInt(values[1]);

                if (operationType.equals(OPPERATION_SUPPLY)) {
                    supplyTotal += amount;
                } else if (operationType.equals(OPPERATION_BUY)) {
                    buyTotal += amount;
                }
            }
            int result = supplyTotal - buyTotal;
            writer.write(OPPERATION_SUPPLY + DELIMITER + supplyTotal);
            writer.newLine();
            writer.write(OPPERATION_BUY + DELIMITER + buyTotal);
            writer.newLine();
            writer.write(RESULT + DELIMITER + result);

        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
    }
}
