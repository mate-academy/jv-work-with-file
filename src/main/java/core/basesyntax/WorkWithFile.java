package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int INDEX_OF_NAME = 0;
    private static final int INDEX_OF_VALUE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFile))) {
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append("; ");
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fromFileName, e);
        }
        String[] fromFileStrings = builder.toString().split("; ");
        int buyCounter = 0;
        int supplyCounter = 0;
        for (String line : fromFileStrings) {
            String[] splitLines = line.split(",");
            if (splitLines[INDEX_OF_NAME].equals("buy")) {
                buyCounter += Integer.parseInt(splitLines[INDEX_OF_VALUE]);
            } else {
                supplyCounter += Integer.parseInt(splitLines[INDEX_OF_VALUE]);
            }
        }
        int result = buyCounter + supplyCounter;
        File toFile = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFile, true))) {
            writer.write("supply," + supplyCounter + "\n");
            writer.write("buy," + buyCounter + "\n");
            writer.write("result," + (supplyCounter - buyCounter));
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + toFileName, e);
        }
    }
}
