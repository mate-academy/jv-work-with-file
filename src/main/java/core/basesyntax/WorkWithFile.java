package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_KEY = "supply";
    private static final String BUY_KEY = "buy";
    private static final String RESULT_KEY = "result";
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] data = getDataFromFile(fromFileName);
        writeDataToFile(toFileName, data);
    }

    private int[] getDataFromFile(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            int buy = 0;
            int supply = 0;
            while (value != null) {
                String[] array = value.split(COMMA);
                if (array[0].equals(SUPPLY_KEY)) {
                    supply += Integer.parseInt(array[1]);
                } else if (array[0].equals(BUY_KEY)) {
                    buy += Integer.parseInt(array[1]);
                }
                value = reader.readLine();
            }
            return new int[]{supply, buy};
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
    }

    private void writeDataToFile(String toFileName, int[] data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(SUPPLY_KEY + COMMA + data[SUPPLY_INDEX]);
            writer.newLine();
            writer.write(BUY_KEY + COMMA + data[BUY_INDEX]);
            writer.newLine();
            writer.write(RESULT_KEY + COMMA + (data[SUPPLY_INDEX] - data[BUY_INDEX]));
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName);
        }
    }
}
