package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String WHITESPACE_CHARACTER = " ";
    private static final String COMMA_CHARACTER = ",";
    private static final String BUY_STRING = "buy";
    private static final String SUPPLY_STRING = "supply";
    private static final String RESULT_STRING = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] arrayOfData = readDataFromFile(fromFileName);
        int[] dataForReport = makeReportFromFileData(arrayOfData);
        writeDataToFile(toFileName, dataForReport);
    }

    private String[] readDataFromFile(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder builder = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(WHITESPACE_CHARACTER);
                value = reader.readLine();
            }
            return builder.toString().split(WHITESPACE_CHARACTER);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file: " + fromFileName, e);
        }
    }

    private int[] makeReportFromFileData(String[] data) {
        int counterBuy = 0;
        int counterSupply = 0;
        int result;
        for (String datum : data) {
            String[] temp = datum.split(COMMA_CHARACTER);
            if (temp[0].equals(SUPPLY_STRING)) {
                counterSupply = counterSupply + Integer.parseInt(temp[1]);
            } else {
                counterBuy = counterBuy + Integer.parseInt(temp[1]);
            }
        }
        result = counterSupply - counterBuy;
        return new int[] {counterSupply, counterBuy, result};
    }

    private void writeDataToFile(String toFileName, int[] report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            StringBuilder builder = new StringBuilder();
            writer.write(builder.append(SUPPLY_STRING).append(COMMA_CHARACTER).append(report[0])
                    .append(System.lineSeparator()).append(BUY_STRING).append(COMMA_CHARACTER)
                    .append(report[1]).append(System.lineSeparator()).append(RESULT_STRING)
                    .append(COMMA_CHARACTER).append(report[2]).toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: " + toFileName, e);
        }
    }
}
