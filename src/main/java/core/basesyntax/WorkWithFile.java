package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int INITIAL_VALUE = 0;
    private static final String DATA_SPLITERATOR = ",";
    private static final String LINE_SEPARATOR = "\n";
    private static final int SUPPLY_POSITION = 0;
    private static final int BUY_POSITION = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readDataFromFile(fromFileName);
        writeDataToFile(data, toFileName);
    }

    private String readDataFromFile(String fromFileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fromFileName))) {
            int supply = INITIAL_VALUE;
            int buy = INITIAL_VALUE;
            String line;
            while ((line = br.readLine()) != null) {
                String[] arr = line.split(DATA_SPLITERATOR);
                int value = Integer.parseInt(arr[BUY_POSITION]);
                int[] counters = new int[]{supply, buy};
                updateCounters(arr[SUPPLY_POSITION], value, counters);
                supply = counters[SUPPLY_POSITION];
                buy = counters[BUY_POSITION];
            }
            return supply + DATA_SPLITERATOR + buy;
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
    }

    private void updateCounters(String type, int value, int[] counters) {
        if (SUPPLY.equals(type)) {
            counters[SUPPLY_POSITION] += value;
        }
        if (BUY.equals(type)) {
            counters[BUY_POSITION] += value;
        }
    }

    private void writeDataToFile(String data, String toFileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(toFileName))) {
            String[] arr = data.split(DATA_SPLITERATOR);
            int supply = Integer.parseInt(arr[SUPPLY_POSITION]);
            int buy = Integer.parseInt(arr[BUY_POSITION]);
            int result = supply - buy;
            bw.write(SUPPLY + DATA_SPLITERATOR + supply + LINE_SEPARATOR);
            bw.write(BUY + DATA_SPLITERATOR + buy + LINE_SEPARATOR);
            bw.write(RESULT + DATA_SPLITERATOR + result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }
}
