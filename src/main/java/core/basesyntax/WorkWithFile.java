package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String DATA_SEPARATOR = ",";
    private static final String BUY_OPERATION = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        Report report = readDataFromFile(fromFileName);
        writeStatisticReportIntoFile(toFileName, report);
    }

    private Report readDataFromFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            int buyAmount = 0;
            int supplyAmount = 0;
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
            return new Report(supplyAmount, buyAmount);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file:" + fromFileName, e);
        }
    }

    private void writeStatisticReportIntoFile(String toFileName, Report report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {

            bufferedWriter.write(report.createReportString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data into the file:" + toFileName, e);
        }
    }
}
