package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_AMOUNT_OFFSET = 1;
    private static final String OPERATION_TYPE_BUY = "buy";
    private static final String OPERATION_TYPE_SUPPLY = "supply";
    private static final String SPLIT_REGEX = "[, ]";
    private static final String RESULT_LABEL = "result";
    private static final String CSV_SEPARATOR = ",";
    private static final String WHITESPACE = " ";

    public void getStatistic(String fromFileName, String toFileName) {
        int buySum = calculateTotalAmountOfOperation(fromFileName, OPERATION_TYPE_BUY);
        int supplySum = calculateTotalAmountOfOperation(fromFileName, OPERATION_TYPE_SUPPLY);
        int result = supplySum - buySum;
        StringBuilder builder = new StringBuilder();
        builder.append(OPERATION_TYPE_SUPPLY).append(CSV_SEPARATOR).append(supplySum)
                .append(System.lineSeparator())
                .append(OPERATION_TYPE_BUY).append(CSV_SEPARATOR).append(buySum)
                .append(System.lineSeparator())
                .append(RESULT_LABEL).append(CSV_SEPARATOR).append(result);

        writeToFIle(toFileName, builder.toString());
    }

    private int calculateTotalAmountOfOperation(String fromFileName, String option) {
        if (!option.equals(OPERATION_TYPE_BUY) && !option.equals(OPERATION_TYPE_SUPPLY)) {
            throw new InvalidOperationOptionException("Wrong option");
        }

        int operationSum = 0;
        String fileData = readFromFile(fromFileName);
        String[] fileDataSplit = fileData.split(SPLIT_REGEX);

        for (int i = 0; i < fileDataSplit.length; i++) {
            if (option.equals(OPERATION_TYPE_SUPPLY) && fileDataSplit[i]
                    .equals(OPERATION_TYPE_SUPPLY)) {
                operationSum += Integer.parseInt(fileDataSplit[i + OPERATION_AMOUNT_OFFSET]);
            } else if (option.equals(OPERATION_TYPE_BUY) && fileDataSplit[i]
                    .equals(OPERATION_TYPE_BUY)) {
                operationSum += Integer.parseInt(fileDataSplit[i + OPERATION_AMOUNT_OFFSET]);
            }
        }
        return operationSum;
    }

    private String readFromFile(String fromFileName) {
        try (BufferedReader input = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder builder = new StringBuilder();
            String value = input.readLine();

            while (value != null) {
                builder.append(value).append(WHITESPACE);
                value = input.readLine();
            }
            return builder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Cannot read from file: " + fromFileName + e);
        }
    }

    private void writeToFIle(String toFileName, String data) {
        try (BufferedWriter output = new BufferedWriter(new FileWriter(toFileName))) {
            output.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write to file: " + toFileName + e);
        }
    }
}
