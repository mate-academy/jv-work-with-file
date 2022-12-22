package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int STRING_INDEX = 0;
    private static final int RESULT_INDEX = 1;
    private static final String SUPPLY_FIELD = "supply";
    private static final String BUY_FIELD = "buy";
    private static final String RESULT_FIELD = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String readData = readReport(fromFileName);
        String report = getReport(readData);
        writeReport(toFileName, report);
    }

    private String readReport(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(" ");
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("I can`t read this file", e);
        }
        return builder.toString();
    }

    private String getReport(String readData) {
        String[] reportPerDay = readData.split(" ");
        StringBuilder resultPerDay = new StringBuilder();
        int buy = 0;
        int supply = 0;
        for (String reports : reportPerDay) {
            String[] str = reports.split(",");
            if (str[STRING_INDEX].equals(SUPPLY_FIELD)) {
                supply += Integer.parseInt(str[RESULT_INDEX]);
            } else {
                buy += Integer.parseInt(str[RESULT_INDEX]);
            }
        }
        resultPerDay.append(SUPPLY_FIELD)
                .append(",")
                .append(supply)
                .append(System.lineSeparator())
                .append(BUY_FIELD)
                .append(",")
                .append(buy)
                .append(System.lineSeparator())
                .append(RESULT_FIELD)
                .append(",")
                .append(supply - buy);
        return resultPerDay.toString();
    }

    private void writeReport(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("I can`t write this data", e);
        }
    }
}
