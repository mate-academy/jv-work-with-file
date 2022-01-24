package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class WorkWithFile {
    private static final String[] OPERATION_TYPE_NAMES = new String[]{"supply", "buy", "result"};
    private static final int OPERATION_TYPES_ARRAY_SIZE = 2;
    private static final int INDEX_OF_TYPE_SUPPLY = 0;
    private static final int INDEX_OF_TYPE_BUY = 1;
    private static final int INDEX_OF_TYPE_RESULT = 2;
    private static final int INDEX_OF_OPERATION_TYPE_IN_LINE = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(toFileName, readFromFile(fromFileName));
    }

    private void writeToFile(String fileName, String dateToWrite) {
        try (BufferedWriter bufferedWriter
                     = new BufferedWriter(new FileWriter(fileName, true))) {
            bufferedWriter.write(dateToWrite);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + e);
        }
    }

    private String readFromFile(String fromFileName) {
        String line = "";
        StringBuilder stringBuilder = new StringBuilder();
        int[] sumOfOperations = new int[OPERATION_TYPES_ARRAY_SIZE];
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            while ((line = bufferedReader.readLine()) != null) {
                String[] currentLine = line.split(",");
                if (currentLine[INDEX_OF_OPERATION_TYPE_IN_LINE]
                        .equals(OPERATION_TYPE_NAMES[INDEX_OF_TYPE_SUPPLY])) {
                    sumOfOperations[INDEX_OF_TYPE_SUPPLY]
                            += Integer.parseInt(currentLine[1]);
                } else if (currentLine[INDEX_OF_OPERATION_TYPE_IN_LINE]
                        .equals(OPERATION_TYPE_NAMES[INDEX_OF_TYPE_BUY])) {
                    sumOfOperations[INDEX_OF_TYPE_BUY] += Integer.parseInt(currentLine[1]);
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + e);
        }
        for (int a = 0; a < sumOfOperations.length; a++) {
            stringBuilder.append(OPERATION_TYPE_NAMES[a]).append(',')
                    .append(sumOfOperations[a]).append(System.lineSeparator());
        }
        stringBuilder.append(OPERATION_TYPE_NAMES[INDEX_OF_TYPE_RESULT]).append(",")
                .append(sumOfOperations[INDEX_OF_TYPE_SUPPLY] - sumOfOperations[INDEX_OF_TYPE_BUY])
                .append(System.lineSeparator());
        return stringBuilder.toString();
    }
}
