package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int SPLIT_LINE_STATUS = 0;
    private static final int SPLIT_LINE_VALUE = 1;
    private static final int SUPPLY = 0;
    private static final int BUY = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int[] supplyAndBuy = {0, 0};
        readFromFile(fromFileName, supplyAndBuy);
        writerToFile(toFileName, supplyAndBuy);
    }

    private void readFromFile(String file, int[] supplyAndBuy) {
        String line;
        String[] splitLine;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            line = reader.readLine();
            while (line != null) {
                splitLine = line.split(",");

                if (splitLine[SPLIT_LINE_STATUS].equals("buy")) {
                    supplyAndBuy[BUY] += Integer.parseInt(splitLine[SPLIT_LINE_VALUE]);
                } else {
                    supplyAndBuy[SUPPLY] += Integer.parseInt(splitLine[SPLIT_LINE_VALUE]);
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file " + file, e);
        }
    }

    private void writerToFile(String file, int[] supplyAndBuy) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("supply," + supplyAndBuy[SUPPLY] + System.lineSeparator()
                    + "buy," + supplyAndBuy[BUY] + System.lineSeparator()
                    + "result," + (supplyAndBuy[SUPPLY] - supplyAndBuy[BUY])
                    + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file ", e);
        }
    }
}
