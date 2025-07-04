package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_OPERATION = "supply";
    private static final String BUY_OPERATION = "buy";
    private static final String RESULT_OPERATION = "result";
    private static final String OPERATION = "operation type";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_VALUE_INDEX = 1;
    private static final String CSV_DELIMITER = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] statisticData = readAndProcessFile(fromFileName);
        writeResultToFile(toFileName, statisticData);
    }

    private String[] readAndProcessFile(String fileName) {

        int totalSupplyAmount = 0;
        int totalBuyAmount = 0;

        try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName))) {
            String currentLine;
            while ((currentLine = fileReader.readLine()) != null) {
                String[] lineValues = currentLine.split(CSV_DELIMITER);
                String operationType = lineValues[OPERATION_TYPE_INDEX];

                if (OPERATION.equals(operationType)) {
                    continue;
                }
                int operationAmount = Integer.parseInt(lineValues[AMOUNT_VALUE_INDEX]);

                if (SUPPLY_OPERATION.equals(operationType)) {
                    totalSupplyAmount += operationAmount;
                } else if (BUY_OPERATION.equals(operationType)) {
                    totalBuyAmount += operationAmount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fileName, e);
        }
        return formatResultData(totalSupplyAmount, totalBuyAmount);
    }

    private void writeResultToFile(String fileName, String[] statisticData) {
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(fileName))) {
            for (String dataLine : statisticData) {
                fileWriter.write(dataLine);
                fileWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file: " + fileName, e);
        }
    }

    private String[] formatResultData(int totalSupplyAmount, int totalBuyAmount) {
        int finalResult = totalSupplyAmount - totalBuyAmount;
        return new String[]{
                SUPPLY_OPERATION + CSV_DELIMITER + totalSupplyAmount,
                BUY_OPERATION + CSV_DELIMITER + totalBuyAmount,
                RESULT_OPERATION + CSV_DELIMITER + finalResult
        };
    }
}
