package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String BUY_WORD = "buy";
    private static final String SUPPLY_WORD = "supply";
    private static final int INDEX_OF_WORD = 0;
    private static final int INDEX_OF_NUMBER = 1;
    private static final String WHITESPACE_SEPARATOR = " ";
    private static final String COMMA_SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readDataFromFile(fromFileName);
        String report = createReport(data);
        writeDataToTheFile(toFileName, report);
    }

    private String[] readDataFromFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append(WHITESPACE_SEPARATOR);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file: " + fileName, e);
        }
        return stringBuilder.toString().split(WHITESPACE_SEPARATOR);
    }

    private String createReport(String[] data) {
        int supplyCounter = 0;
        int buyCounter = 0;
        String[] operationData;
        for (String partOfData : data) {
            operationData = partOfData.split(COMMA_SEPARATOR);
            String operation = operationData[INDEX_OF_WORD];
            String operationAmount = operationData[INDEX_OF_NUMBER];
            if (operation.equals(BUY_WORD)) {
                buyCounter += Integer.parseInt(operationAmount);
            } else if (operation.equals(SUPPLY_WORD)) {
                supplyCounter += Integer.parseInt(operationAmount);
            }
        }
        StringBuilder result = new StringBuilder();
        return result.append(SUPPLY_WORD).append(COMMA_SEPARATOR)
                .append(supplyCounter).append(System.lineSeparator())
                .append(BUY_WORD).append(COMMA_SEPARATOR).append(buyCounter)
                .append(System.lineSeparator()).append("result,")
                .append((supplyCounter - buyCounter)).toString();
    }

    private void writeDataToTheFile(String fileName, String data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file: " + fileName, e);
        }
    }
}
