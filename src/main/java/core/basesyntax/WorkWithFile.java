package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int SPLIT_LINE_STATUS = 0;
    private static final int SPLIT_LINE_VALUE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int buy = 0;
        int supply = 0;
        String[] splitLine;
        String line;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            line = reader.readLine();
            while (line != null) {
                splitLine = line.split(",");

                if (splitLine[SPLIT_LINE_STATUS].equals("buy")) {
                    buy += Integer.parseInt(splitLine[SPLIT_LINE_VALUE]);
                } else {
                    supply += Integer.parseInt(splitLine[SPLIT_LINE_VALUE]);
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file " + fromFileName, e);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + supply + System.lineSeparator()
                    + "buy," + buy + System.lineSeparator()
                    + "result," + (supply - buy) + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file ", e);
        }
    }
}
