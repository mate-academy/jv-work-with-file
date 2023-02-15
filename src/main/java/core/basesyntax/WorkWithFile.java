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
        String dataForWriting = makeReportFromFileData(arrayOfData);
        writeDataToFile(toFileName, dataForWriting);
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

    private String makeReportFromFileData(String[] data) {
        int counterBuy = 0;
        int counterSupply = 0;
        for (String datum : data) {
            String[] datumParts = datum.split(COMMA_CHARACTER);
            if (datumParts[0].equals(SUPPLY_STRING)) {
                counterSupply = counterSupply + Integer.parseInt(datumParts[1]);
            } else {
                counterBuy = counterBuy + Integer.parseInt(datumParts[1]);
            }
        }
        int result = counterSupply - counterBuy;
        StringBuilder builder = new StringBuilder();

        return builder.append(SUPPLY_STRING).append(COMMA_CHARACTER).append(counterSupply)
                .append(System.lineSeparator()).append(BUY_STRING).append(COMMA_CHARACTER)
                .append(counterBuy).append(System.lineSeparator()).append(RESULT_STRING)
                .append(COMMA_CHARACTER).append(result).toString();
    }

    private void writeDataToFile(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: " + toFileName, e);
        }
    }
}
