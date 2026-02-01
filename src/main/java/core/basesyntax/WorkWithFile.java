package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] statistics = readStatistics(fromFileName);
        writeStatistics(toFileName, statistics);
    }

    private int[] readStatistics(String fileName) {
        int supply = 0;
        int buy = 0;
        String line;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (BUY.equals(parts[0])) {
                    buy += Integer.parseInt(parts[1]);
                } else if (SUPPLY.equals(parts[0])) {
                    supply += Integer.parseInt(parts[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read this file", e);
        }

        return new int[]{supply, buy};
    }

    private void writeStatistics(String fileName, int[] statistics) {
        int supply = statistics[0];
        int buy = statistics[1];
        int result = supply - buy;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(SUPPLY + "," + supply + System.lineSeparator());
            writer.write(BUY + "," + buy + System.lineSeparator());
            writer.write("result," + result + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
