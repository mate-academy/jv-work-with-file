package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR = ",";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String SUPPLY_OPERATION_TYPE = "supply";
    private static final String BUY_OPERATION_TYPE = "buy";
    private static final String REPORT_RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromFile = readFromFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(report, toFileName);
    }

    private String[] readFromFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line = bufferedReader.readLine();

            while (line != null) {
                stringBuilder.append(line).append(" ");
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fileName, e);
        }

        if (stringBuilder.isEmpty()) {
            return new String[0];
        }

        return stringBuilder.toString().split(" ");
    }

    private String createReport(String[] dataForReport) {
        StringBuilder stringBuilder = new StringBuilder();
        int supplyTotalAmount = 0;
        int buyTotalAmount = 0;

        for (String data: dataForReport) {
            if (data == null) {
                continue;
            }

            String[] partsOdData = data.split(SEPARATOR);

            if (partsOdData[OPERATION_TYPE_INDEX].equals(SUPPLY_OPERATION_TYPE)) {
                supplyTotalAmount += Integer.parseInt(partsOdData[AMOUNT_INDEX]);
            }

            if (partsOdData[OPERATION_TYPE_INDEX].equals(BUY_OPERATION_TYPE)) {
                buyTotalAmount += Integer.parseInt(partsOdData[AMOUNT_INDEX]);
            }
        }

        stringBuilder.append(formatReportLine(SUPPLY_OPERATION_TYPE, supplyTotalAmount))
                .append(System.lineSeparator())
                .append(formatReportLine(BUY_OPERATION_TYPE, buyTotalAmount))
                .append(System.lineSeparator())
                .append(formatReportLine(REPORT_RESULT, supplyTotalAmount - buyTotalAmount));

        return stringBuilder.toString();
    }

    private String formatReportLine(String fieldName, int fieldValue) {
        return fieldName + SEPARATOR + fieldValue;
    }

    private void writeToFile(String data, String fileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(data);

        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + fileName, e);
        }
    }
}
