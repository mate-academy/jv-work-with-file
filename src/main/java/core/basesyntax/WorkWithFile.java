package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMMA = ",";
    private static final int OPERATION_INDEX = 0;
    private static final int COUNT = 1;
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String info = readFromFile(fromFileName);
        String report = generateReport(info);
        writeReportInFile(toFileName, report);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fromFileName, e);
        }
        return stringBuilder.toString();
    }

    private String generateReport(String info) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] operations = info.split(System.lineSeparator());
        int totalSupply = 0;
        int totalBuy = 0;
        for (String operation : operations) {
            String[] separate = operation.split(COMMA);
            if (separate[OPERATION_INDEX].equals(SUPPLY)) {
                totalSupply += Integer.parseInt(separate[COUNT]);
            }
            if (separate[OPERATION_INDEX].equals(BUY)) {
                totalBuy += Integer.parseInt(separate[COUNT]);
            }
        }
        stringBuilder.append(SUPPLY).append(COMMA).append(totalSupply)
                .append(System.lineSeparator())
                .append(BUY).append(COMMA).append(totalBuy)
                .append(System.lineSeparator())
                .append(RESULT).append(COMMA)
                .append(totalSupply - totalBuy);
        return stringBuilder.toString();
    }

    private void writeReportInFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write info" + toFileName, e);
        }
    }
}
