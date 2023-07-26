package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String LINE_SEPARATOR_CSV = ";";
    private static final String COLUMN_SEPARATOR_CSV = ",";
    private static final String REPORT_GROUP_SUPPLY = "supply";
    private static final String REPORT_GROUP_BUY = "buy";
    private static final String REPORT_GROUP_RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] inputCsvData = readCsvFile(fromFileName);

        String[] reportData = createReportData(inputCsvData);

        if (reportData.length != 0) {
            writeRepotToFile(reportData, toFileName);
        }
    }

    private String[] readCsvFile(String fileName) {
        File input = new File(fileName);
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(input))) {
            String value = bufferedReader.readLine();

            while (value != null) {
                stringBuilder.append(value).append(LINE_SEPARATOR_CSV);
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fileName, e);
        }

        return convertStringToArray(stringBuilder.toString());
    }

    private String[] convertStringToArray(String inputData) {
        if (inputData.length() != 0) {
            return inputData.split(LINE_SEPARATOR_CSV);
        } else {
            return new String[0];
        }
    }

    private String[] createReportData(String[] inputCsvData) {
        StringBuilder stringBuilder = new StringBuilder();
        int totalSupply = 0;
        int totalBuy = 0;

        for (int i = 0; i < inputCsvData.length; i++) {
            String[] csvRecord = inputCsvData[i].split(COLUMN_SEPARATOR_CSV);
            String csvGroup = csvRecord[0];
            int csvGroupAmount = Integer.parseInt(csvRecord[1]);

            if (csvGroup.equals(REPORT_GROUP_SUPPLY)) {
                totalSupply += csvGroupAmount;
            } else if (csvGroup.equals(REPORT_GROUP_BUY)) {
                totalBuy += csvGroupAmount;
            }
        }

        stringBuilder.append(REPORT_GROUP_SUPPLY).append(COLUMN_SEPARATOR_CSV).append(totalSupply)
                        .append(System.lineSeparator())
                    .append(REPORT_GROUP_BUY).append(COLUMN_SEPARATOR_CSV).append(totalBuy)
                        .append(System.lineSeparator())
                    .append(REPORT_GROUP_RESULT).append(COLUMN_SEPARATOR_CSV)
                    .append(totalSupply - totalBuy)
                    .append(System.lineSeparator());

        return stringBuilder.toString().split(System.lineSeparator());
    }

    private void writeRepotToFile(String[] reportData, String outputFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFileName))) {
            for (String record:reportData) {
                bufferedWriter.write(record + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file:" + outputFileName, e);
        }
    }
}
