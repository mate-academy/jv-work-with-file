package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int AMOUNT_OF_OPERATIONS = 2;
    private static final String DATA_SEPARATOR = ",";
    private final String[] operationNames = new String[AMOUNT_OF_OPERATIONS];
    private final int[] operationAmounts = new int[AMOUNT_OF_OPERATIONS];

    public void getStatistic(String fromFileName, String toFileName) {
        fillArrayWithOperations();
        readDataFromFileIntoArray(fromFileName);
        writeStatisticReportIntoFile(toFileName);
        clearNamesArray();
        clearAmountsArray();
    }

    private void fillArrayWithOperations() {
        operationNames[0] = "buy";
        operationNames[1] = "supply";
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
            int supplyValue = operationAmounts[findValuePositionByOperationName("supply")];
            int buyValue = operationAmounts[findValuePositionByOperationName("buy")];
            Report report = new Report(supplyValue, buyValue);
            bufferedWriter.write(report.createReportString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data into the file", e);
        }
    }

    private int findValuePositionByOperationName(String operationName) {
        for (int i = 0; i < operationNames.length; i++) {
            if (operationNames[i].equals(operationName)) {
                return i;
            }
        }
        return -1;
    }

    private void clearNamesArray() {
        for (int i = 0; i < operationNames.length; i++) {
            operationNames[i] = null;
        }
    }

    private void clearAmountsArray() {
        for (int i = 0; i < operationAmounts.length; i++) {
            operationAmounts[i] = 0;
        }
    }
}
