package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String OPERATION_SUPPLY = "supply";
    private static final String OPERATION_BUY = "buy";
    private static final String OPERATION_RESULT = "result";
    private static final String SPLIT_REGEX = ",";
    private static final int INDEX_FOR_OPERATION_TYPE = 0;
    private static final int INDEX_FOR_AMMOUNT = 1;
    private static final int INDEX_FOR_RESULT = 2;
    private static final int REPORT_ARRAY_LENGTH = 3;

    public void getStatistic(String fromFileName, String toFileName) {
        int[] reportDataArray = createReport(fromFileName);
        writeToFile(toFileName, reportDataArray);
    }

    private void writeToFile(String toFileName, int[] reportDataArray) {
        StringBuilder stringBuilder = new StringBuilder();
        String reportData = stringBuilder
                .append(OPERATION_SUPPLY).append(SPLIT_REGEX)
                .append(reportDataArray[INDEX_FOR_OPERATION_TYPE])
                .append(System.lineSeparator())
                .append(OPERATION_BUY).append(SPLIT_REGEX)
                .append(reportDataArray[INDEX_FOR_AMMOUNT])
                .append(System.lineSeparator())
                .append(OPERATION_RESULT).append(SPLIT_REGEX)
                .append(reportDataArray[INDEX_FOR_RESULT])
                .toString();
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write(reportData);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + e);
        }
    }

    private int[] createReport(String fromFileName) {
        int[] dataArray = new int[REPORT_ARRAY_LENGTH];
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String stringOfFile = bufferedReader.readLine();
            while (stringOfFile != null) {
                String[] splitString = stringOfFile.split(SPLIT_REGEX);
                for (String word : splitString) {
                    if (word.equals(OPERATION_SUPPLY)) {
                        dataArray[INDEX_FOR_OPERATION_TYPE]
                                += Integer.parseInt(splitString[INDEX_FOR_AMMOUNT]);
                    } else if (word.equals(OPERATION_BUY)) {
                        dataArray[INDEX_FOR_AMMOUNT]
                                += Integer.parseInt(splitString[INDEX_FOR_AMMOUNT]);
                    }
                }
                stringOfFile = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        dataArray[INDEX_FOR_RESULT]
                = dataArray[INDEX_FOR_OPERATION_TYPE] - dataArray[INDEX_FOR_AMMOUNT];
        return dataArray;
    }

}
