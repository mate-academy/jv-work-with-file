package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    static final String SUPPLY_WORD = "supply";
    static final String BUY_WORD = "buy";
    static final String RESULT_WORD = "result";
    static final int NAME_COLUMN_INDEX = 0;
    static final int VALUE_COLUMN_INDEX = 1;
    static final int SUPPLY_INDEX = 0;
    static final int BUY_INDEX = 1;
    static final String DATA_SEPARATOR = ",";
    static final String RECORD_SEPARATOR = ";";

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFile = readFromFile(fromFileName);
        String dataReport = countReportResult(dataFile);
        writeToFile(dataReport,toFileName);
    }

    private String readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder stringBuilder = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(RECORD_SEPARATOR);
                value = reader.readLine();
            }
            String fromFileString = stringBuilder.toString();
            if (fromFileString.isEmpty()) {
                return new String();
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("can't read file");
        }
    }

    private String countReportResult(String input) {
        String[] records = input.split(RECORD_SEPARATOR);
        final int[] resultValues = new int[]{0, 0};
        for (String record : records) {
            String[] dataRecord = record.split(DATA_SEPARATOR);
            if (dataRecord[NAME_COLUMN_INDEX].equals(SUPPLY_WORD)) {
                resultValues[SUPPLY_INDEX] += Integer.parseInt(dataRecord[VALUE_COLUMN_INDEX]);
            } else if (dataRecord[NAME_COLUMN_INDEX].equals(BUY_WORD)) {
                resultValues[BUY_INDEX] += Integer.parseInt(dataRecord[VALUE_COLUMN_INDEX]);
            }
        }
        String result = createReportString(resultValues);
        return result;
    }

    private String createReportString(int[] resultValues) {
        StringBuilder builder = new StringBuilder();
        builder.append(SUPPLY_WORD).append(DATA_SEPARATOR).append(resultValues[SUPPLY_INDEX]);
        builder.append(System.lineSeparator());
        builder.append(BUY_WORD).append(DATA_SEPARATOR).append(resultValues[BUY_INDEX]);
        builder.append(System.lineSeparator());
        int resultAmount = resultValues[SUPPLY_INDEX] - resultValues[BUY_INDEX];
        builder.append(RESULT_WORD).append(DATA_SEPARATOR).append(resultAmount);
        builder.append(System.lineSeparator());
        return builder.toString();
    }

    private void writeToFile(String output,String toFileName) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(output);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
