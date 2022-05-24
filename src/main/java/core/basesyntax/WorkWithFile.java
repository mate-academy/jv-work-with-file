package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final int ACTION = 0;
    private static final int VALUE = 1;
    private static final String SEPARATOR = System.lineSeparator();
    private static final String SPACE = " ";
    private static final String COMMA = ",";
    private static final String RESULT = "result";

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
            if (splitRecord[ACTION].equals(SUPPLY)) {
                supplyCount = supplyCount + Integer.parseInt(splitRecord[VALUE]);
            }
            if (splitRecord[ACTION].equals(BUY)) {
                buyCount = buyCount + Integer.parseInt(splitRecord[VALUE]);
            }
        }
        int result = supplyCount - buyCount;
        return SUPPLY + COMMA + supplyCount + SEPARATOR
                + BUY + COMMA + buyCount + SEPARATOR
                + RESULT + COMMA + result;
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
