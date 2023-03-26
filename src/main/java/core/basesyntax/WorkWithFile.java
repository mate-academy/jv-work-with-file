package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final byte BUY_INDEX = 0;
    private static final byte SUPPLY_INDEX = 1;
    private static final String SUPPLY_OPERATION = "supply";
    private static final String BUY_OPERATION = "buy";
    private static final String RESULT_OPERATION = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] fromFileData = readFile(fromFileName).split("\\W+");
        int[] totalAmounts = calculateSum(fromFileData);
        writeFile(toFileName, totalAmounts);
    }

    private String readFile(String fromFile) {
        File file = new File(fromFile);
        StringBuilder textBox = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFile))) {
            if (file.length() == 0) {
                System.out.println("File: " + fromFile + "is empty.");
            }
            int symbol = 0;
            while ((symbol = reader.read()) != -1) {
                textBox.append((char) symbol);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fromFile, e);
        }
        return textBox.toString();
    }

    private int[] calculateSum(String[] dataArray) {
        int[] operationValues = {0,0};
        for (int i = 0; i < dataArray.length; i++) {
            dataArray[i] = dataArray[i].toLowerCase();
            if (dataArray[i].equals(SUPPLY_OPERATION)) {
                operationValues[SUPPLY_INDEX] += Integer.parseInt(dataArray[i + 1]);
            } else if (dataArray[i].equals(BUY_OPERATION)) {
                operationValues[BUY_INDEX] += Integer.parseInt(dataArray[i + 1]);
            }
        }
        return operationValues;
    }

    private void writeFile(String toFile, int[] operationsSum) {
        File file = new File(toFile);
        int resultSum = operationsSum[SUPPLY_INDEX] - operationsSum[BUY_INDEX];
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, false))) {
            bufferedWriter.write(SUPPLY_OPERATION + "," + operationsSum[SUPPLY_INDEX]
                    + System.lineSeparator() + BUY_OPERATION + ","
                    + operationsSum[BUY_INDEX] + System.lineSeparator()
                    + RESULT_OPERATION + "," + resultSum + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFile, e);
        }
    }
}
