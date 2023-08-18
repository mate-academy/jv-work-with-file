package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final int INT_VALUE_INDEX = 1;
    private static final int STRING_VALUE_INDEX = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(createReport(stringDataReport(fromFileName)),
                new File(toFileName));
    }

    private String[] stringDataReport(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        String value;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            while ((value = bufferedReader.readLine()) != null) {
                stringBuilder.append(value).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fromFileName, e);
        }
        return stringBuilder.toString().split(System.lineSeparator());
    }

    private String createReport(String[] text) {
        int supply = 0;
        int buy = 0;
        String[] strings;
        for (String string : text) {
            strings = string.split(SEPARATOR);
            if (strings[STRING_VALUE_INDEX].equals(SUPPLY)) {
                supply += Integer.valueOf(strings[INT_VALUE_INDEX]);
            } else if (strings[STRING_VALUE_INDEX].equals(BUY)) {
                buy += Integer.valueOf(strings[INT_VALUE_INDEX]);
            }
        }
        int result = supply - buy;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY).append(SEPARATOR).append(supply).append(System.lineSeparator())
                .append(BUY).append(SEPARATOR).append(buy).append(System.lineSeparator())
                .append(RESULT).append(SEPARATOR).append(result).append(System.lineSeparator());
        return stringBuilder.toString();
    }

    private void writeToFile(String stats, File toFileName) {
        try (BufferedWriter bufferedWriter =
                new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(stats);
        } catch (IOException e) {
            throw new RuntimeException("Can't write this file: " + toFileName, e);
        }
    }
}
