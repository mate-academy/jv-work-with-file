package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String RECORD_DATA_DIVIDER = ",";
    private static final String OPERATIONS_DIVIDER = " ";
    private static final int FIRST_PART = 0;
    private static final int SECOND_PART = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readData(fromFileName);
        String report = makeReport(dataFromFile);
        recordReportToFile(report, toFileName);
    }

    private String readData(String file) {
        StringBuilder fileRecord = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String value = reader.readLine();
            while (value != null) {
                fileRecord.append(value).append(RECORD_DATA_DIVIDER).append(OPERATIONS_DIVIDER);
                value = reader.readLine();
            }
            return fileRecord.toString().trim();
        } catch (IOException e) {
            throw new RuntimeException("Can't read data!", e);
        }
    }

    private String makeReport(String record) {
        int supply = 0;
        int buy = 0;
        int result = 0;
        String[] recordArr = record.split(OPERATIONS_DIVIDER);

        for (String word: recordArr) {
            String[] recordArrParts = word.split(RECORD_DATA_DIVIDER);
            if (recordArrParts[FIRST_PART].equals("supply")) {
                supply += Integer.parseInt(recordArrParts[SECOND_PART]);
            }
            if (recordArrParts[FIRST_PART].equals("buy")) {
                buy += Integer.parseInt(recordArrParts[SECOND_PART]);
            }
        }
        result = supply - buy;

        StringBuilder finalReport = new StringBuilder();
        finalReport.append("supply,").append(supply).append("\n")
                .append("buy,").append(buy).append("\n")
                .append("result,").append(result);
        return finalReport.toString();
    }

    private void recordReportToFile(String record, String file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(record);
        } catch (IOException e) {
            throw new RuntimeException("Can't record report to a file", e);
        }
    }
}
