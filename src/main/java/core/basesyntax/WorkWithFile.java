package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMMA = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] supplyAndBuySums = readData(fromFileName);
        String report = createReport(supplyAndBuySums);
        writeReport(toFileName, report);
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

    private String createReport(int[] supplyAndBuySums) {
        int supplySum = supplyAndBuySums[0];
        int buySum = supplyAndBuySums[1];
        int result = calculateResult(supplySum, buySum);

        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append(SUPPLY).append(COMMA).append(supplySum).append(System.lineSeparator());
        reportBuilder.append(BUY).append(COMMA).append(buySum).append(System.lineSeparator());
        reportBuilder.append(RESULT).append(COMMA).append(result).append(System.lineSeparator());

        return reportBuilder.toString();
    }

    private int calculateResult(int supplySum, int buySum) {
        return supplySum - buySum;
    }

    private void writeReport(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can not write data to the file " + toFileName, e);
        }
    }

    public static void main(String[] args) {
        WorkWithFile calculator = new WorkWithFile();
        calculator.getStatistic("input.txt", "output.txt");
    }
}
