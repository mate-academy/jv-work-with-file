package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final byte buySum = 0;
    private static final byte supplySum = 1;
    private static final String supplyOperation = "supply";
    private static final String buyOperation = "buy";
    private static final String result = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] fromFileData = readFile(fromFileName).toString().split("\\W+");
        int[] totalAmounts = calculateSum(fromFileData);
        writeFile(toFileName, totalAmounts);
    }

    private StringBuilder readFile(String fromFile) {
        File file = new File(fromFile);
        StringBuilder textBox = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFile))) {
            File checkFile = new File(fromFile);
            if (checkFile.length() == 0) {
                System.out.println("File: " + fromFile + "is empty.");
            }
            int symbol = 0;
            while ((symbol = reader.read()) != -1) {
                textBox.append((char) symbol);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fromFile, e);
        }
        return textBox;
    }

    private int[] calculateSum(String[] dataArray) {
        int[] operationValues = {0,0};
        for (int i = 0; i < dataArray.length; i++) {
            dataArray[i] = dataArray[i].toLowerCase();
            if (dataArray[i].equals(supplyOperation)) {
                operationValues[supplySum] += Integer.parseInt(dataArray[i + 1]);
            } else if (dataArray[i].equals(buyOperation)) {
                operationValues[buySum] += Integer.parseInt(dataArray[i + 1]);
            }
        }
        return operationValues;
    }

    private void writeFile(String toFile, int[] operationsSum) {
        File file = new File(toFile);
        int resultSum = operationsSum[supplySum] - operationsSum[buySum];
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, false))) {
            bufferedWriter.write(supplyOperation + "," + operationsSum[supplySum]
                    + System.lineSeparator() + buyOperation + ","
                    + operationsSum[buySum] + System.lineSeparator()
                    + result + "," + resultSum + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFile, e);
        }
    }
}
