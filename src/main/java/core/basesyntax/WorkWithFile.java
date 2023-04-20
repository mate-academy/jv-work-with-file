package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> dataFromBuffer = readFromFile(fromFileName);
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

    private List<String> readFromFile(String fromFileName) {
        List<String> dataFromBuffer = new ArrayList<>();
        String line;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            while ((line = bufferedReader.readLine()) != null) {
                dataFromBuffer.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(TEXT_OF_EXCEPTION, e);
        }
        return dataFromBuffer;
    }

    private String createReportMessage(int supplyAmount, int buyAmount) {
        return new StringBuilder().append(OPERATION_SUPPLY).append(COMA_SEPARATOR)
                .append(supplyAmount)
                .append(System.lineSeparator())
                .append(OPERATION_BUY).append(COMA_SEPARATOR).append(buyAmount)
                .append(System.lineSeparator())
                .append(OPERATION_RESULT).append(COMA_SEPARATOR)
                .append(supplyAmount - buyAmount)
                .toString();
    }

    private int calculateStatisticAmount(List<String> dataFromBuffer, String operation) {
        int amount = 0;
        for (String lineFromBuffer : dataFromBuffer) {
            String[] splitString = lineFromBuffer.split(DELIMITER);
            if (splitString[AMOUNT_INDEX_0].equals(operation)) {
                amount += Integer.parseInt(splitString[AMOUNT_INDEX_1]);
            }
        }
        return amount;
    }
}
