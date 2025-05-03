package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String RECORD_DATA_DIVIDER = ",";
    private static final int FIRST_PART = 0;
    private static final int SECOND_PART = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

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
                fileRecord.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
            return fileRecord.toString().trim();
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from a " + file + "file!", e);
        }
    }

    private String makeReport(String record) {
        String[] recordArr = record.split(System.lineSeparator());
        int supply = 0;
        int buy = 0;

        for (String word: recordArr) {
            String[] recordArrParts = word.split(RECORD_DATA_DIVIDER);
            int quantity = Integer.parseInt(recordArrParts[SECOND_PART]);
            if (recordArrParts[FIRST_PART].equals(SUPPLY)) {
                supply += quantity;
            }
            if (recordArrParts[FIRST_PART].equals(BUY)) {
                buy += quantity;
            }
        }
        int result = supply - buy;

        return new StringBuilder()
                .append(SUPPLY).append(RECORD_DATA_DIVIDER)
                .append(supply).append(System.lineSeparator())
                .append(BUY).append(RECORD_DATA_DIVIDER).append(buy).append(System.lineSeparator())
                .append(RESULT).append(RECORD_DATA_DIVIDER).append(result)
                .toString();
    }

    private void recordReportToFile(String record, String file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(record);
        } catch (IOException e) {
            throw new RuntimeException("Can't record report to a " + file + " file", e);
        }
    }
}
