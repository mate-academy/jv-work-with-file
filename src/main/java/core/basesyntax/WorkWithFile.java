package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMMA = ",";
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int OPERATION_VALUE_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromFile = readFromFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(toFileName, report);
    }

    private String createReport(String[] dataFromFile) {
        int buy = 0;
        int supply = 0;
        StringBuilder builder = new StringBuilder();
        for (String line : dataFromFile) {
            String[] records = line.split(COMMA);
            if (records[OPERATION_TYPE_INDEX].equals(BUY)) {
                buy += Integer.valueOf(records[OPERATION_VALUE_INDEX]);
            }
            if (records[OPERATION_TYPE_INDEX].equals(SUPPLY)) {
                supply += Integer.valueOf(records[OPERATION_VALUE_INDEX]);
            }
        }
        int result = supply - buy;
        return builder.append(SUPPLY + COMMA).append(supply).append(System.lineSeparator())
                .append(BUY + COMMA).append(buy).append(System.lineSeparator())
                .append(RESULT + COMMA).append(result).toString();
    }

    private String[] readFromFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder builder = new StringBuilder();
            int value = bufferedReader.read();
            while (value != -1) {
                builder.append((char) value);
                value = bufferedReader.read();
            }
            return builder.toString().split(System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Error during reading file: " + fromFileName, e);
        }
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Unable to write content to file: " + toFileName, e);
        }
    }
}
