package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    private static final String NEWLINE_CHARACTER = System.lineSeparator();
    private static final String DATA_SEPARATOR_CHARACTER = ",";
    private static final int DATA_COLUMNS = 2;
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String[] REPORT_DATA = new String[] {"supply", "buy", "result"};
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;
    private static final int RESULT_INDEX = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        String[][] data = readFromFile(fromFileName);
        String report = createReport(data);
        writeToFile(report, toFileName);
    }

    private void writeToFile(String report, String toFileName) {
        File file = new File(toFileName);
        try {
            Files.write(file.toPath(), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't create or write data to the file " + toFileName, e);
        }
    }

    private String createReport(String[][] dataRowsAndColumns) {
        StringBuilder report = new StringBuilder();
        int[] reportDataAmounts = new int[REPORT_DATA.length];
        for (int i = 0; i < REPORT_DATA.length; i++) {
            report.append(REPORT_DATA[i]).append(DATA_SEPARATOR_CHARACTER);
            reportDataAmounts[i] = (i != RESULT_INDEX)
                    ? calculateAmountOfOperationType(dataRowsAndColumns, REPORT_DATA[i])
                    : reportDataAmounts[SUPPLY_INDEX] - reportDataAmounts[BUY_INDEX];
            report.append(reportDataAmounts[i]);
            if (i != RESULT_INDEX) {
                report.append(System.lineSeparator());
            }
        }
        return report.toString();
    }

    private int calculateAmountOfOperationType(String[][] data, String operationType) {
        int amount = 0;
        for (String[] row : data) {
            if (row[OPERATION_TYPE_INDEX].equals(operationType)) {
                amount += Integer.parseInt(row[AMOUNT_INDEX]);
            }
        }
        return amount;
    }

    private String[][] readFromFile(String fileName) {
        String fileText = convertFileTextToString(fileName);
        String[] dataRows = fileText.split(NEWLINE_CHARACTER);
        String[][] data = new String[dataRows.length][DATA_COLUMNS];
        for (int i = 0; i < dataRows.length; i++) {
            data[i] = dataRows[i].split(DATA_SEPARATOR_CHARACTER);
        }
        return data;
    }

    private String convertFileTextToString(String fileName) {
        File file = new File(fileName);
        StringBuilder fileText = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String value = reader.readLine();
            while (value != null) {
                fileText.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fileName, e);
        }
        return fileText.toString();
    }
}
