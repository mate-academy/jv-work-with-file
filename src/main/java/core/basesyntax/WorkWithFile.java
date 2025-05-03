package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String OPERATION_SUPPLY = "supply";
    private static final String OPERATION_BUY = "buy";
    private static final String OPERATION_RESULT = "result";
    private static final String COMA_SEPARATOR = ",";
    private static final String TEXT_OF_EXCEPTION = "File not found, check that the file path"
            + " is correct and that the file still exists in the specified location";
    private static final int AMOUNT_INDEX_0 = 0;
    private static final int AMOUNT_INDEX_1 = 1;
    private static final String DELIMITER = "\\W";
    private static final String SPLIT_DELIMITER = "!%=&!";

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromBuffer = readFromFile(fromFileName);
        int supplyAmount = calculateStatisticAmount(dataFromBuffer, OPERATION_SUPPLY);
        int buyAmount = calculateStatisticAmount(dataFromBuffer, OPERATION_BUY);
        String reportMessage = createReportMessage(supplyAmount, buyAmount);
        writeStringToFile(toFileName, reportMessage);
    }

    private void writeStringToFile(String toFileName, String reportMessage) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(reportMessage);
        } catch (IOException e) {
            throw new RuntimeException(TEXT_OF_EXCEPTION, e);
        }
    }

    private String readFromFile(String fromFileName) {
        StringBuilder dataFromBuffer = new StringBuilder();
        String line;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            while ((line = bufferedReader.readLine()) != null) {
                dataFromBuffer.append(line).append(SPLIT_DELIMITER);
            }
        } catch (IOException e) {
            throw new RuntimeException(TEXT_OF_EXCEPTION, e);
        }
        return dataFromBuffer.toString();
    }

    private String createReportMessage(int supplyAmount, int buyAmount) {
        return OPERATION_SUPPLY + COMA_SEPARATOR + supplyAmount + System.lineSeparator()
                + OPERATION_BUY + COMA_SEPARATOR + buyAmount + System.lineSeparator()
                + OPERATION_RESULT + COMA_SEPARATOR + (supplyAmount - buyAmount);
    }

    private int calculateStatisticAmount(String dataFromBuffer, String operation) {
        int amount = 0;
        String[] arrayWithDataFromBuffer = dataFromBuffer.split(SPLIT_DELIMITER);
        for (String lineFromBuffer : arrayWithDataFromBuffer) {
            String[] splitString = lineFromBuffer.split(DELIMITER);
            if (splitString[AMOUNT_INDEX_0].equals(operation)) {
                amount += Integer.parseInt(splitString[AMOUNT_INDEX_1]);
            }
        }
        return amount;
    }
}
