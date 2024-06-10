package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] supplyAndBuySums = readData(fromFileName);
        int supplySum = supplyAndBuySums[0];
        int buySum = supplyAndBuySums[1];
        int result = calculateResult(supplySum, buySum);
        writeReport(toFileName, supplySum, buySum, result);
    }

    private int[] readData(String fromFileName) {
        int supplySum = 0;
        int buySum = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(COMMA);
                String operationType = data[0];
                int amount = Integer.parseInt(data[1]);

                if (operationType.equals(SUPPLY)) {
                    supplySum += amount;
                } else if (operationType.equals(BUY)) {
                    buySum += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can not read data from the file " + fromFileName, e);
        }

        return new int[]{supplySum, buySum};
    }

    private int calculateResult(int supplySum, int buySum) {
        return supplySum - buySum;
    }

    private void writeReport(String toFileName, int supplySum, int buySum, int result) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(SUPPLY + COMMA + supplySum + System.lineSeparator());
            bufferedWriter.write(BUY + COMMA + buySum + System.lineSeparator());
            bufferedWriter.write(RESULT + COMMA + result + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Can not write data to the file " + toFileName, e);
        }
    }
}
