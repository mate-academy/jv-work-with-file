package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class WorkWithFile {
    private static final int DEFAULT_AMOUNT_VALUE = 0;
    private static final int AMOUNT_OF_OPERATIONS = 2;
    private static final String DATA_SEPARATOR = ",";
    private static final String BUY_OPERATION = "buy";

    private final int[] operationAmounts = new int[AMOUNT_OF_OPERATIONS];

    public void getStatistic(String fromFileName, String toFileName) {
        readDataFromFileIntoArray(fromFileName);
        writeStatisticReportIntoFile(toFileName);
        clearArraysAfterReportCreating();
    }

    private void readDataFromFileIntoArray(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String dataLine = bufferedReader.readLine();
            while (dataLine != null) {
                String[] operationAmountDataPair = dataLine.split(DATA_SEPARATOR);
                String operation = operationAmountDataPair[0];
                int amount = Integer.parseInt(operationAmountDataPair[1]);
                operationAmounts[findValuePositionByOperationName(operation)] += amount;
                dataLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file", e);
        }
    }

    private void writeStatisticReportIntoFile(String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter(toFileName))) {
            int buyValue = operationAmounts[0];
            int supplyValue = operationAmounts[1];
            Report report = new Report(supplyValue, buyValue);
            bufferedWriter.write(report.createReportString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data into the file", e);
        }
    }

    private int findValuePositionByOperationName(String operationName) {
        return operationName.equals(BUY_OPERATION) ? 0 : 1;
    }

    public void clearArraysAfterReportCreating() {
        Arrays.fill(operationAmounts, DEFAULT_AMOUNT_VALUE);
    }
}
