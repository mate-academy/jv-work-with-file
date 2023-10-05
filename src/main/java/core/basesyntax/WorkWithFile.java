package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String DATA_SEPARATOR = ",";
    private static final String BUY_OPERATION = "buy";
    private int buyAmount;
    private int supplyAmount;

    public WorkWithFile() {
        buyAmount = 0;
        supplyAmount = 0;
    }

    public void getStatistic(String fromFileName, String toFileName) {
        readDataFromFile(fromFileName);
        writeStatisticReportIntoFile(toFileName);
    }

    private void readDataFromFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String dataLine = bufferedReader.readLine();
            while (dataLine != null) {
                String[] operationAmountDataPair = dataLine.split(DATA_SEPARATOR);
                String operation = operationAmountDataPair[0];
                int amount = Integer.parseInt(operationAmountDataPair[1]);
                if (operation.equals(BUY_OPERATION)) {
                    buyAmount += amount;
                } else {
                    supplyAmount += amount;
                }
                dataLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file:" + fromFileName, e);
        }
    }

    private void writeStatisticReportIntoFile(String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            Report report = new Report(supplyAmount, buyAmount);
            bufferedWriter.write(report.createReportString());
            clearAmounts();
        } catch (IOException e) {
            throw new RuntimeException("Can't write data into the file:" + toFileName, e);
        }
    }

    private void clearAmounts() {
        buyAmount = 0;
        supplyAmount = 0;
    }
}
