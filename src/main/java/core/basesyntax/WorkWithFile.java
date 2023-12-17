package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        int[] statistics = readStatistic(fromFileName);
        writeStatistic(toFileName, statistics[0], statistics[1]);
    }

    private int[] readStatistic(String fromFileName) {
        int supply = 0;
        int buy = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            String value = reader.readLine();

            while (value != null) {
                String[] values = value.split(",");
                int amount = Integer.parseInt(values[1]);
                if ("supply".equals(values[0])) {
                    supply += amount;
                } else {
                    buy += amount;
                }
                value = reader.readLine();
            }
            return new int[] {supply, buy};
        } catch (IOException e) {
            throw new RuntimeException("Error reading from file", e);
        }
    }

    private void writeStatistic(String toFileName, int supply, int buy) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + supply + System.lineSeparator()
                    + "buy," + buy + System.lineSeparator()
                    + "result," + (supply -= buy));
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file", e);
        }
    }
}
