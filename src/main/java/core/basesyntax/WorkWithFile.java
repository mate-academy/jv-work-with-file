package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {
    private static final String OPERATION_TYPE_SUPPLY = "supply";
    private static final String OPERATION_TYPE_BUY = "buy";
    private static final String COMMA_SEPARATOR = ",";
    private static final int INDEX_OF_OPERATION_TYPE_VALUE = 0;
    private static final int INDEX_OF_AMOUNT_VALUE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int supplySum = getOperationTypeAmountSum(fromFileName, OPERATION_TYPE_SUPPLY);
        int buySum = getOperationTypeAmountSum(fromFileName, OPERATION_TYPE_BUY);
        writeToFile(toFileName, report(supplySum, buySum));
    }

    private int getOperationTypeAmountSum(String fileName, String operationType) {
        try (BufferedReader file = new BufferedReader(new FileReader(fileName))) {
            String line = file.readLine();
            int amountSum = 0;
            while (line != null) {
                amountSum += getAmountValue(line, operationType);
                line = file.readLine();
            }
            return amountSum;
        } catch (Exception e) {
            throw new RuntimeException("Can't read data from the file " + fileName, e);
        }
    }

    private int getAmountValue(String line, String operationType) {
        String[] lineValues = line.split(COMMA_SEPARATOR);
        String operationTypeValue = lineValues[INDEX_OF_OPERATION_TYPE_VALUE];
        int amountValue = Integer.parseInt(lineValues[INDEX_OF_AMOUNT_VALUE]);
        return operationTypeValue.equals(operationType) ? amountValue : 0;
    }

    private void writeToFile(String toFile, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            bufferedWriter.write(report);
        } catch (Exception e) {
            throw new RuntimeException("Can't write data to the file " + toFile, e);
        }
    }

    private String report(int supplySum, int buySum) {
        StringBuilder result = new StringBuilder();
        result.append(OPERATION_TYPE_SUPPLY).append(",").append(supplySum)
                .append(System.lineSeparator())
                .append(OPERATION_TYPE_BUY).append(",").append(buySum)
                .append(System.lineSeparator())
                .append("result,").append(supplySum - buySum);
        return result.toString();
    }
}
