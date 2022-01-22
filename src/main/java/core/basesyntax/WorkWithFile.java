package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private int supply;
    private int buy;
    private int result;
    private StringBuilder stringBuilder = new StringBuilder();
    private String report;

    public void getStatistic(String fromFileName, String toFileName) {
        readInfo(fromFileName);
        calculateResult();
        createReport();
        writeInfoToReport(report, toFileName);
    }

    private void readInfo(String fromFile) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFile));
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file:" + fromFile, e);
        }
    }

    private void calculateResult() {
        buy = 0;
        supply = 0;
        result = 0;
        String[] info = stringBuilder.toString().split(System.lineSeparator());
        for (String str : info) {
            if (str.substring(0, str.indexOf(",")).equals(SUPPLY)) {
                supply += Integer.parseInt(str.substring(str.indexOf(",") + 1));
            } else if (str.substring(0, str.indexOf(",")).equals(BUY)) {
                buy += Integer.parseInt(str.substring(str.indexOf(",") + 1));
            }
        }
        result = supply - buy;
    }

    private void createReport() {
        StringBuilder stringBuilderForReport = new StringBuilder();
        stringBuilderForReport.append(SUPPLY).append(",").append(supply)
                .append(System.lineSeparator()).append(BUY).append(",").append(buy)
                .append(System.lineSeparator())
                .append(RESULT).append(",").append(result);
        report = stringBuilderForReport.toString();
    }

    private void writeInfoToReport(String info, String toFile) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile, true))) {
            bufferedWriter.write(info);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file:" + toFile, e);
        }
    }
}
