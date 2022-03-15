package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String BUY_OPERATION_KEY = "buy";
    private static final String SUPPLY_OPERATION_KEY = "supply";
    private static final String RESULT_KEY = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = getDataFromFile(fromFileName);
        String[] splitData = dataFromFile.split(System.lineSeparator());

        int countBuyOperation = 0;
        int countSupplyOperation = 0;
        for (int i = 0; i < splitData.length; i++) {
            String[] operationTypeAndAmount = splitData[i].split(",");

            if (operationTypeAndAmount[0].equals(BUY_OPERATION_KEY)) {
                countBuyOperation += Integer.parseInt(operationTypeAndAmount[1]);
            }

            if (operationTypeAndAmount[0].equals(SUPPLY_OPERATION_KEY)) {
                countSupplyOperation += Integer.parseInt(operationTypeAndAmount[1]);
            }
        }

        int result = countSupplyOperation - countBuyOperation;

        String dataReport = prepareStatisticDataReport(countSupplyOperation,
                countBuyOperation, result);
        writeDataToFile(toFileName, dataReport);
    }

    private String getDataFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }

            return stringBuilder.toString();

        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fromFileName, e);
        }
    }

    private String prepareStatisticDataReport(int countSupplyOperation, int countBuyOperation,
                                              int result) {
        return new StringBuilder()
                .append(SUPPLY_OPERATION_KEY).append(",").append(countSupplyOperation)
                .append(System.lineSeparator())
                .append(BUY_OPERATION_KEY).append(",").append(countBuyOperation)
                .append(System.lineSeparator())
                .append(RESULT_KEY).append(",").append(result)
                .append(System.lineSeparator()).toString();
    }

    private void writeDataToFile(String toFileName, String dataReport) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write(dataReport);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + toFileName, e);
        }
    }
}
