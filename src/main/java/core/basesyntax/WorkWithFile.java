package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    public static final String SUPPLY_FIELD = "supply";
    public static final String BUY_FIELD = "buy";
    public static final String RESULT_FIELD = "result";
    public static final String SEPARATOR = ",";
    public static final String INITIAL_AMOUNT = "0";

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        List<String> originalReport = readReport(file);
        String[][] finishedReport = getStatsArray(originalReport);
        file = new File(toFileName);
        writeReport(file, finishedReport);
    }

    private String[][] getStatsArray(List<String> strings) {
        String[][] statsArray = {{SUPPLY_FIELD, INITIAL_AMOUNT},
                {BUY_FIELD, INITIAL_AMOUNT},
                {RESULT_FIELD, INITIAL_AMOUNT}};
        String[] fieldArray;
        for (String field : strings) {
            fieldArray = field.split(SEPARATOR);
            if (fieldArray[0].equals(SUPPLY_FIELD)) {
                statsArray[0][1] = Integer.toString(Integer.parseInt(statsArray[0][1])
                        + Integer.parseInt(fieldArray[1]));
            } else {
                statsArray[1][1] = Integer.toString(Integer.parseInt(statsArray[1][1])
                        + Integer.parseInt(fieldArray[1]));
            }
        }

        statsArray[2][1] = Integer.toString(Integer.parseInt(statsArray[0][1])
                - Integer.parseInt(statsArray[1][1]));
        return statsArray;
    }

    private List<String> readReport(File file) {
        try {
            return Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("File reading error.", e);
        }
    }

    private void writeReport(File file, String[][] report) {
        StringBuilder stringToWrite = new StringBuilder();
        for (String[] strings : report) {
            stringToWrite.append(strings[0]).append(SEPARATOR)
                    .append(strings[1]).append(System.lineSeparator());
        }
        try {
            Files.writeString(file.toPath(), stringToWrite.toString());
        } catch (IOException e) {
            throw new RuntimeException("File writing error.", e);
        }
    }
}
