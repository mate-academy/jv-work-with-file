package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result,";
    private static final int INDEX_REPORT_INFO = 0;
    private static final int INDEX_REPORT_AMOUNT = 1;
    private StringBuilder stringBuilder = new StringBuilder();

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFromFile(fromFileName);
        String report = createReport(data);
        writeReportToFile(toFileName, report);
    }

    private String readFromFile(String fileName) {
        String value;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(" ");
                value = bufferedReader.readLine();
            }
            value = stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("File can't be read", e);
        }
        return value;
    }

    private String createReport(String data) {
        String[] reportData = data.split(" ");
        int supply = 0;
        int buy = 0;
        for (String datum : reportData) {
            String[] info = datum.split(",");
            if (info[INDEX_REPORT_INFO].equals(SUPPLY)) {
                supply += Integer.parseInt(info[INDEX_REPORT_AMOUNT]);
            } else {
                buy += Integer.parseInt(info[INDEX_REPORT_AMOUNT]);
            }
        }
        int result = supply - buy;
        stringBuilder = new StringBuilder(SUPPLY)
                .append(",")
                .append(supply)
                .append(System.lineSeparator())
                .append(BUY)
                .append(",")
                .append(buy)
                .append(System.lineSeparator())
                .append(RESULT)
                .append(result);
        return stringBuilder.toString();
    }

    private void writeReportToFile(String fileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
