package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                if (value.split(",")[0].equals(SUPPLY)) {
                    supply += Integer.parseInt(value.split(",")[1]);
                } else {
                    buy += Integer.parseInt(value.split(",")[1]);
                }
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
        writeStatistic(toFileName, buy, supply);
    }

    private void writeStatistic(String toFileName, int buy, int supply) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            String report = new StringBuilder()
                    .append("supply,").append(supply).append(System.lineSeparator())
                    .append("buy,").append(buy).append(System.lineSeparator())
                    .append("result,").append(supply - buy).toString();
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }
}
