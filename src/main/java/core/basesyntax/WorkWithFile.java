package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {
    private static final String supplyRow = "supply";
    private static final String buyRow = "buy";
    private static final String lineSeparator = ",";
    private static String operationTypeValue;
    private static int amountValue;

    public void getStatistic(String fromFileName, String toFileName) {
        int supplySum = getFromFileSumOf(fromFileName, supplyRow);
        int buySum = getFromFileSumOf(fromFileName, buyRow);
        writeToFile(toFileName, supplySum, buySum);
    }

    private int getFromFileSumOf(String fromFileName, String rowNameValue) {
        try (BufferedReader file = new BufferedReader(new FileReader(fromFileName))) {
            String line = file.readLine();
            int amountSum = 0;

            while (line != null) {
                lineSplit(line);
                amountSum += getAmountValue(rowNameValue);
                line = file.readLine();
            }
            return amountSum;
        } catch (Exception e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
    }

    private int getAmountValue(String word) {
        return operationTypeValue.equals(word) ? amountValue : 0;
    }

    private void lineSplit(String line) {
        String[] lineValues = line.split(lineSeparator);
        operationTypeValue = lineValues[0];
        amountValue = Integer.parseInt(lineValues[1]);
    }

    private int getResultValue(int supplySum, int buySum) {
        return supplySum - buySum;
    }

    private void writeToFile(String toFileName, int supplySum, int buySum) {
        try (BufferedWriter toFile = new BufferedWriter(new FileWriter(toFileName))) {
            toFile.write(supplyRow + "," + supplySum + System.lineSeparator()
                    + buyRow + "," + buySum + System.lineSeparator()
                    + "result," + getResultValue(supplySum, buySum));
        } catch (Exception e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }
}
