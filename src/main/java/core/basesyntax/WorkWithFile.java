package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    public static final String SUPPLY_FIELD = "supply";
    public static final String BUY_FIELD = "buy";
    public static final String RESULT_FIELD = "result";
    public static final int SUPPLY_FIELD_INDEX = 0;
    public static final int BUY_FIELD_INDEX = 1;
    public static final int RESULT_FIELD_INDEX = 2;
    public static final int NAME_FIELD_INDEX = 0;
    public static final int AMOUNT_FIELD_INDEX = 1;
    public static final String SEPARATOR = ",";
    public static final String INITIAL_AMOUNT = "0";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> originalReport = readReport(fromFileName);
        String report = generateReport(getStatsArray(originalReport));
        writeReport(toFileName, report);
    }

    private String[][] getStatsArray(List<String> strings) {
        String[][] statsArray = {{SUPPLY_FIELD, INITIAL_AMOUNT},
                {BUY_FIELD, INITIAL_AMOUNT},
                {RESULT_FIELD, INITIAL_AMOUNT}};
        String[] fieldArray;
        for (String field : strings) {
            fieldArray = field.split(SEPARATOR);
            if (fieldArray[NAME_FIELD_INDEX].equals(SUPPLY_FIELD)) {
                statsArray[SUPPLY_FIELD_INDEX][AMOUNT_FIELD_INDEX] = Integer.toString(
                        Integer.parseInt(statsArray[SUPPLY_FIELD_INDEX][AMOUNT_FIELD_INDEX])
                        + Integer.parseInt(fieldArray[AMOUNT_FIELD_INDEX]));
            } else {
                statsArray[BUY_FIELD_INDEX][1] = Integer.toString(Integer.parseInt(
                        statsArray[BUY_FIELD_INDEX][AMOUNT_FIELD_INDEX])
                        + Integer.parseInt(fieldArray[AMOUNT_FIELD_INDEX]));
            }
        }

        statsArray[RESULT_FIELD_INDEX][1] = Integer.toString(
                Integer.parseInt(statsArray[SUPPLY_FIELD_INDEX][AMOUNT_FIELD_INDEX])
                - Integer.parseInt(statsArray[BUY_FIELD_INDEX][AMOUNT_FIELD_INDEX]));
        return statsArray;
    }

    private List<String> readReport(String fromFileName) {
        try {
            return Files.readAllLines(new File(fromFileName).toPath());
        } catch (IOException e) {
            throw new RuntimeException("File reading error.", e);
        }
    }

    private void writeReport(String toFileName, String report) {
        try {
            Files.writeString(new File(toFileName).toPath(), report);
        } catch (IOException e) {
            throw new RuntimeException("File writing error.", e);
        }
    }

    private String generateReport(String[][] report) {
        StringBuilder stringToWrite = new StringBuilder();
        for (String[] strings : report) {
            stringToWrite.append(strings[NAME_FIELD_INDEX]).append(SEPARATOR)
                    .append(strings[AMOUNT_FIELD_INDEX]).append(System.lineSeparator());
        }
        return stringToWrite.toString();
    }
}
