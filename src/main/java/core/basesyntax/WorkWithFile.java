package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String SUPPLY_FIELD = "supply";
    private static final String BUY_FIELD = "buy";
    private static final String RESULT_FIELD = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFile(fromFileName);
        String report = getReport(data);
        writeReport(toFileName, report);
    }

    private String readFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(" ");
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("I can`t read this file" + fromFileName, e);
        }
        return builder.toString();
    }

    private String getReport(String operationsData) {
        String[] operationsReport = operationsData.split(" ");
        StringBuilder operationsResult = new StringBuilder();
        int buy = 0;
        int supply = 0;
        for (String reports : operationsReport) {
            String[] oneOperation = reports.split(",");
            if (oneOperation[OPERATION_INDEX].equals(SUPPLY_FIELD)) {
                supply += Integer.parseInt(oneOperation[AMOUNT_INDEX]);
            } else {
                buy += Integer.parseInt(oneOperation[AMOUNT_INDEX]);
            }
        }
        operationsResult.append(SUPPLY_FIELD)
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
        return operationsResult.toString();
    }

    private void writeReport(String toFileName, String text) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(text);
        } catch (IOException e) {
            throw new RuntimeException("I can`t write this data" + toFileName, e);
        }
    }
}
