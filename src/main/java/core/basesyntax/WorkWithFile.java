package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE_NUMBER = 0;
    private static final int OPERATION_VALUE_NUMBER = 1;
    private static final int SUPPLY_SUM_INDEX = 0;
    private static final int BUY_SUM_INDEX = 1;
    private static final String SUPPLY_NAME = "supply,";
    private static final String BUY_NAME = "buy,";
    private static final String RESULT_NAME = "result,";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] wordsFromFile = readFile(fromFileName);
        int[] listOfTwoSums = calculateSums(wordsFromFile);
        String completedReport = writeReport(listOfTwoSums);
        writeToFile(completedReport, toFileName);
    }

    private String[] readFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String value = reader.readLine();
            StringBuilder stringBuilder = new StringBuilder();
            while (value != null) {
                stringBuilder.append(value).append('.');
                value = reader.readLine();
            }
            return stringBuilder.toString().split("\\.");
        } catch (IOException e) {
            throw new RuntimeException("Can't find file with name [" + fileName + "]!", e);
        }
    }

    private int[] calculateSums(String[] linesFromFile) {
        int supplySum = 0;
        int buySum = 0;
        for (String line : linesFromFile) {
            String[] splitedLine = line.split(",");
            String operationType = splitedLine[OPERATION_TYPE_NUMBER];
            int operationValue = Integer.parseInt(splitedLine[OPERATION_VALUE_NUMBER]);
            if (operationType.equals("supply")) {
                supplySum += operationValue;
            } else if (operationType.equals("buy")) {
                buySum += operationValue;
            }
        }
        return new int[]{supplySum, buySum};
    }

    private String writeReport(int[] calculatedSums) {
        int supplySum = calculatedSums[SUPPLY_SUM_INDEX];
        int buySum = calculatedSums[BUY_SUM_INDEX];
        return SUPPLY_NAME + supplySum + System.lineSeparator() + BUY_NAME + buySum
                + System.lineSeparator() + RESULT_NAME + (supplySum - buySum)
                + System.lineSeparator();
    }

    private void writeToFile(String message, String fileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(message);
        } catch (IOException e) {
            throw new RuntimeException("Can't write in file with name [" + fileName + "]!", e);
        }
    }
}
