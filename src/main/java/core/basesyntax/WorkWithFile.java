package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String DELIMITER = ",";
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";
    private static final int NAME_INDEX = 0;
    private static final int MONEY_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readFile(fromFileName);
        writeReport(toFileName, createReport(data));
    }

    private String[] readFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String content = reader.readLine();
            while (content != null) {
                builder.append(content).append(System.lineSeparator());
                content = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't find a file: " + fromFileName, e);
        }
        return builder.toString().split(System.lineSeparator());
    }

    private String createReport(String[] data) {
        StringBuilder builder = new StringBuilder();
        String[] contentArray;
        int buy = 0;
        int supply = 0;
        for (String info : data) {
            contentArray = info.split(DELIMITER);
            if (contentArray[NAME_INDEX].equals(BUY)) {
                buy += Integer.parseInt(contentArray[MONEY_INDEX]);
            } else if (contentArray[NAME_INDEX].equals(SUPPLY)) {
                supply += Integer.parseInt(contentArray[MONEY_INDEX]);
            }
        }
        builder.append(SUPPLY).append(DELIMITER).append(supply).append(System.lineSeparator())
                .append(BUY).append(DELIMITER).append(buy).append(System.lineSeparator())
                .append(RESULT).append(DELIMITER).append(supply - buy);
        return builder.toString();
    }

    private void writeReport(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file: " + toFileName, e);
        }
    }
}
