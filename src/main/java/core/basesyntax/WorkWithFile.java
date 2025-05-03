package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int ACTION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String DELIVERY_FIELD = "supply";
    private static final String PURCHASE_FIELD = "buy";
    private static final String RESULT_OF_THE_DAY = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String readData = readReport(fromFileName);
        String report = getReport(readData);
        writeReport(toFileName, report);
    }

    private String readReport(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(" ");
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("File is empty" + e);
        }
        return stringBuilder.toString();
    }

    private String getReport(String readData) {
        String[] listFromDay = readData.split(" ");
        StringBuilder resultOfDay = new StringBuilder();
        int countOfBuy = 0;
        int countOfSupply = 0;
        for (String s : listFromDay) {
            String[] oneLine = s.split(",");
            if (oneLine[ACTION_INDEX].equals(DELIVERY_FIELD)) {
                countOfSupply += Integer.parseInt(oneLine[AMOUNT_INDEX]);
            } else {
                countOfBuy += Integer.parseInt(oneLine[AMOUNT_INDEX]);
            }
        }
        resultOfDay.append(DELIVERY_FIELD)
                .append(",")
                .append(countOfSupply)
                .append(System.lineSeparator())
                .append(PURCHASE_FIELD).append(",")
                .append(countOfBuy)
                .append(System.lineSeparator())
                .append(RESULT_OF_THE_DAY)
                .append(",")
                .append(countOfSupply - countOfBuy);
        return resultOfDay.toString();
    }

    private void writeReport(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write to the file" + e);
        }
    }
}
