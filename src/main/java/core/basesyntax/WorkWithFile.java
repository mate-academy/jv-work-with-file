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
    private static final String SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] statistic = readStatistic(fromFileName);
        writeStatistic(toFileName, statistic[0], statistic[1]);
    }

    private int[] readStatistic(String fromFileName) {
        int supply = 0;
        int buy = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(SEPARATOR);
                int amount = Integer.parseInt(data[1]);

                if (SUPPLY.equals(data[0])) {
                    supply += amount;
                } else {
                    buy += amount;
                }
            }
            return new int[]{supply, buy};
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fromFileName, e);
        }
    }

    private void writeStatistic(String toFileName, int supply, int buy) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            writeRecord(bufferedWriter, SUPPLY, supply);
            writeRecord(bufferedWriter, BUY, buy);
            writeRecord(bufferedWriter, RESULT, supply - buy);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file: " + toFileName, e);
        }
    }

    private void writeRecord(BufferedWriter bufferedWriter,
                             String key, int value) throws IOException {
        bufferedWriter.write(key + SEPARATOR + value);
        bufferedWriter.newLine();
    }
}
