package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String OPERATION_SUPPLY = "supply";
    private static final String OPERATION_BUY = "buy";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        try {
            int[] arr = readFile(fromFileName);
            String result = getResult(arr);
            writeFile(toFileName, result);
        } catch (RuntimeException e) {
            throw new RuntimeException("An error occurred while reading or writing files: ", e);
        }
    }

    private int[] readFile(String fileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            int supplySum = 0;
            int buySum = 0;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] operation = line.split(COMMA);
                final String operationName = operation[0];
                final String operationValue = operation[1];
                int parsedValue = Integer.parseInt(operationValue);
                if (operationName.equals(OPERATION_SUPPLY)) {
                    supplySum += parsedValue;
                } else {
                    buySum += parsedValue;
                }
            }
            return new int[]{supplySum, buySum};
        } catch (IOException e) {
            throw new RuntimeException("An error occurred while reading the file: " + fileName, e);
        }
    }

    private void writeFile(String fileName, String result) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(result);
        } catch (IOException e) {
            throw new RuntimeException("An error occurred while writing the file: " + fileName, e);
        }
    }

    private String getResult(int[] arr) {
        StringBuilder builder = new StringBuilder();
        builder.append(OPERATION_SUPPLY).append(COMMA)
                .append(arr[0]).append(System.lineSeparator());
        builder.append(OPERATION_BUY).append(COMMA)
                .append(arr[1]).append(System.lineSeparator());
        builder.append("result").append(COMMA)
                .append(arr[0] - arr[1]).append(System.lineSeparator());
        return builder.toString();
    }
}
