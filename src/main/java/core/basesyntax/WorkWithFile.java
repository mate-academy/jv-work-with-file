package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int INDEX_SUPPLY = 0;
    private static final int INDEX_BUY = 1;
    private static final int INDEX_OPERATION = 0;
    private static final int INDEX_QUANTITY = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int[] data = calculateSupplyAndBuy(fromFileName);
        int result = data[INDEX_SUPPLY] - data[INDEX_BUY];
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + data[INDEX_SUPPLY] + System.lineSeparator()
                    + "buy," + data[INDEX_BUY] + System.lineSeparator()
                    + "result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }

    private int[] calculateSupplyAndBuy(String fromFileName) {
        int supply = 0;
        int buy = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line = reader.readLine();
            while (line != null) {
                if (line.split(",")[INDEX_OPERATION].equals("supply")) {
                    supply += Integer.parseInt(line.split(",")[INDEX_QUANTITY]);
                } else {
                    buy += Integer.parseInt(line.split(",")[INDEX_QUANTITY]);
                }
                line = reader.readLine();
            }
            return new int[]{supply, buy};
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }
}
