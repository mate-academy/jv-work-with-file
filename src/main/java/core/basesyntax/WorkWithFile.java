package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String OPERATION_SUPPLY = "supply";
    private static final String OPERATION_BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) throws RuntimeException {
        int supplyTotal = 0;
        int buyTotal = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
                BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] splitLine = line.split(",");
                String operation = splitLine[0];
                int amount = Integer.parseInt(splitLine[1]);

                if (operation.equals(OPERATION_SUPPLY)) {
                    supplyTotal += amount;
                } else if (operation.equals(OPERATION_BUY)) {
                    buyTotal += amount;
                }
            }

            int result = supplyTotal - buyTotal;

            writer.write(String.format("%s,%d%n", OPERATION_SUPPLY, supplyTotal));
            writer.write(String.format("%s,%d%n", OPERATION_BUY, buyTotal));
            writer.write(String.format("%s,%d", RESULT, result));

        } catch (IOException e) {
            throw new RuntimeException("Can't write/read to/from file",e);
        }
    }
}
