package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String[] OPERATION_TYPE_NAMES = new String[]{"supply", "buy", "result"};
    private static final String SEPARATOR = ",";
    private static final int OPERATION_TYPES_ARRAY_SIZE = 2;
    private static final int INDEX_OF_TYPE_SUPPLY = 0;
    private static final int INDEX_OF_TYPE_BUY = 1;
    private static final int INDEX_OF_TYPE_RESULT = 2;
    private static final int INDEX_OF_OPERATION_TYPE_IN_LINE = 0;
    private int[] sumOfOperations = new int[OPERATION_TYPES_ARRAY_SIZE];

    public void getStatistic(String fromFileName, String toFileName) {
        readFromFile(fromFileName);
        writeToFile(toFileName, createReport());
    }

    private void writeToFile(String fileName, String dateToWrite) {
        try (BufferedWriter bufferedWriter
                     = new BufferedWriter(new FileWriter(fileName, true))) {
            bufferedWriter.write(dateToWrite);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + e);
        }
    }

    private void readFromFile(String fromFileName) {
        String line = "";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            while ((line = bufferedReader.readLine()) != null) {
                String[] currentLine = line.split(SEPARATOR);
                if (currentLine[INDEX_OF_OPERATION_TYPE_IN_LINE]
                        .equals(OPERATION_TYPE_NAMES[INDEX_OF_TYPE_SUPPLY])) {
                    sumOfOperations[INDEX_OF_TYPE_SUPPLY]
                            += Integer.parseInt(currentLine[1]);
                } else if (currentLine[INDEX_OF_OPERATION_TYPE_IN_LINE]
                        .equals(OPERATION_TYPE_NAMES[INDEX_OF_TYPE_BUY])) {
                    sumOfOperations[INDEX_OF_TYPE_BUY] += Integer.parseInt(currentLine[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + e);
        }
    }

    private String createReport() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int a = 0; a < sumOfOperations.length; a++) {
            stringBuilder.append(OPERATION_TYPE_NAMES[a]).append(SEPARATOR)
                    .append(sumOfOperations[a]).append(System.lineSeparator());
        }
        stringBuilder.append(OPERATION_TYPE_NAMES[INDEX_OF_TYPE_RESULT]).append(SEPARATOR)
                .append(sumOfOperations[INDEX_OF_TYPE_SUPPLY] - sumOfOperations[INDEX_OF_TYPE_BUY])
                .append(System.lineSeparator());
        return stringBuilder.toString();
    }
}
