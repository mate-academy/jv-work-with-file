package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String DELIMITER = ",";
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String SUPPLY_TYPE = "supply";
    private static final String BUY_TYPE = "buy";
    private static final String RESULT_TYPE = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readDataFromFile(fromFileName);
        String report = createReport(data);
        writeDataToFile(toFileName, report);
    }

    private String[] readDataFromFile(String filename) {
        StringBuilder supplyBuyInfo = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            String line = bufferedReader.readLine();

            while (line != null) {
                supplyBuyInfo.append(line).append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
        } catch (IOException ex) {
            throw new RuntimeException("Can't read data from " + filename, ex);
        }

        return supplyBuyInfo.toString().split(System.lineSeparator());
    }

    private String createReport(String[] data) {
        StringBuilder report = new StringBuilder();
        int buyAmount = 0;
        int supplyAmount = 0;

        for (String line : data) {
            String[] operationInfo = line.split(DELIMITER);
            String operationType = operationInfo[OPERATION_INDEX];
            int amount = Integer.parseInt(operationInfo[AMOUNT_INDEX]);

            if (operationType.equals(SUPPLY_TYPE)) {
                supplyAmount += amount;
            } else {
                buyAmount += amount;
            }
        }

        int totalAmount = supplyAmount - buyAmount;
        report.append(SUPPLY_TYPE).append(DELIMITER).append(supplyAmount)
                .append(System.lineSeparator())
                .append(BUY_TYPE).append(DELIMITER).append(buyAmount)
                .append(System.lineSeparator())
                .append(RESULT_TYPE).append(DELIMITER).append(totalAmount);

        return report.toString();
    }

    private void writeDataToFile(String filename, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename))) {
            bufferedWriter.write(report);
        } catch (IOException ex) {
            throw new RuntimeException("Can't write data to " + filename, ex);
        }
    }
}
