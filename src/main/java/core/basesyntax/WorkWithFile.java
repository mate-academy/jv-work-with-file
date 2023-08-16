package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String NEW_LINE = System.lineSeparator();
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] strings = readData(fromFileName);
        int supplyAmount = calculateAmountOfOperation(strings, SUPPLY);
        int buyAmount = calculateAmountOfOperation(strings, BUY);
        int resultAmount = supplyAmount - buyAmount;
        String report = createReport(supplyAmount, buyAmount, resultAmount);
        writeToFile(toFileName, report);
    }

    private String[] readData(String fromFile) {
        StringBuilder textFromFile = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFile))) {
            String line = reader.readLine();
            while (line != null) {
                textFromFile.append(NEW_LINE).append(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read data from the file " + fromFile, e);
        }
        return textFromFile.toString().split(NEW_LINE);
    }

    private int calculateAmountOfOperation(String[] operationsData, String operation) {
        int amount = 0;
        for (String line : operationsData) {
            String[] operationAndAmount = line.split(COMMA);
            String currentOperation = operationAndAmount[0];
            if (operationAndAmount.length == 2 && currentOperation.equals(operation)) {
                String currentAmount = operationAndAmount[1];
                amount += Integer.parseInt(currentAmount);
            }
        }
        return amount;
    }

    private String createReport(int supplyAmount, int buyAmount, int resultAmount) {
        StringBuilder dataOfReport = new StringBuilder().append(SUPPLY).append(COMMA)
                .append(supplyAmount).append(NEW_LINE).append(BUY).append(COMMA).append(buyAmount)
                .append(NEW_LINE).append(RESULT).append(COMMA).append(resultAmount);
        return dataOfReport.toString();
    }

    private void writeToFile(String toFile, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFile))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to the file " + toFile, e);
        }
    }
}
