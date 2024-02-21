package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMMA = ",";
    private static final int OPERATION = 0;
    private static final int COUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String info = getInfo(fromFileName);
        String report = writeReport(info);
        writeReportInFile(toFileName, report);
    }

    private String getInfo(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return stringBuilder.toString();
    }

    private String writeReport(String info) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] operations = info.split(System.lineSeparator());
        int totalSupply = 0;
        int totalBuy = 0;
        for (String operation : operations) {
            String[] separate = operation.split(COMMA);
            if (separate[OPERATION].equals("supply")) {
                totalSupply += Integer.parseInt(separate[COUNT]);
            }
            if (separate[OPERATION].equals("buy")) {
                totalBuy += Integer.parseInt(separate[COUNT]);
            }
        }
        stringBuilder.append("supply").append(COMMA).append(totalSupply)
                .append(System.lineSeparator())
                .append("buy").append(COMMA).append(totalBuy)
                .append(System.lineSeparator())
                .append("result").append(totalSupply - totalBuy);
        return stringBuilder.toString();
    }

    private void writeReportInFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write info", e);
        }
    }
}
