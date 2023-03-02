package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final int OPERATION_INDEX = 0;
    private static final int QUANTITY_INDEX = 1;

    public static void getStatistic(String fromFileName, String toFileName) {

        int supplyCount = 0;
        int buyCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] informationRead = line.split(",");
                String splittedLine = informationRead[OPERATION_INDEX];
                int amount = Integer.parseInt(informationRead[QUANTITY_INDEX]);
                if (splittedLine.equals(SUPPLY)) {
                    supplyCount += amount;
                } else if (splittedLine.equals(BUY)) {
                    buyCount += amount;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Can't read data from file" + fromFileName, e);
        }

        int result = supplyCount - buyCount;

        try (FileWriter writer = new FileWriter(toFileName)) {
            writer.write(SUPPLY + "," + supplyCount + System.lineSeparator());
            writer.write(BUY + "," + buyCount + System.lineSeparator());
            writer.write(RESULT + "," + result + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + toFileName, e);
        }
    }
}
