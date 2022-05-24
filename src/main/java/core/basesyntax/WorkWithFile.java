package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";
    public static final int ITEM = 0;
    public static final int VALUE = 1;
    public static final String SEPARATOR = System.lineSeparator();
    public static final String SPACE = " ";
    public static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] rawData = readData(fromFileName);
        String report = createReport(rawData);
        writeData(report, toFileName);
    }

    private String[] readData(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        File fromFile = new File(fromFileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFile))) {
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(SPACE);
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file " + fromFileName, e);
        }
        return builder.toString().split(SPACE);
    }

    private String createReport(String[] data) {
        int supplyCount = 0;
        int buyCount = 0;
        for (String record : data) {
            String[] splitRecord = record.split(COMMA);
            if (splitRecord[ITEM].equals(SUPPLY)) {
                supplyCount = supplyCount + Integer.parseInt(splitRecord[VALUE]);
            }
            if (splitRecord[ITEM].equals(BUY)) {
                buyCount = buyCount + Integer.parseInt(splitRecord[VALUE]);
            }
        }
        int result = supplyCount - buyCount;
        return "supply," + supplyCount + SEPARATOR
                + "buy," + buyCount + SEPARATOR
                + "result," + result;
    }

    private void writeData(String report, String toFileName) {
        File toFile = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFile))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }
}
