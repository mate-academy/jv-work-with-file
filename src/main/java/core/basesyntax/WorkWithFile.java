package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int AMOUNT_INDEX = 1;
    private static final int OPERATION_INDEX = 0;
    private static final String SEPARATOR = ",";
    private static final String BUY_OPERATION = "buy";
    private static final String SUPPLY_OPERATION = "supply";
    private static final String RESULT_OPERATION = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFromFile(fromFileName);
        data = processData(data);
        writeToFile(toFileName, data);
    }

    private static String readFromFile(String filename) {
        StringBuilder information = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                information.append(line).append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read from file with name: " + filename, e);
        }
        return information.toString();
    }

    private void writeToFile(String fileName, String data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file with name: " + fileName, e);
        }
    }

    private String processData(String data) {
        int buySum = 0;
        int supplySum = 0;
        String[] splitData;
        String[] dataArray = data.split(System.lineSeparator());
        for (int i = 0; i < dataArray.length; i++) {
            splitData = dataArray[i].split(SEPARATOR);
            if (splitData[OPERATION_INDEX].equals(SUPPLY_OPERATION)) {
                supplySum += Integer.parseInt(splitData[AMOUNT_INDEX]);
            } else {
                buySum += Integer.parseInt(splitData[AMOUNT_INDEX]);
            }
        }
        return createResult(buySum, supplySum);
    }

    private String createResult(int buySum, int supplySum) {
        String result = SUPPLY_OPERATION
                + SEPARATOR
                + supplySum
                + System.lineSeparator()
                + BUY_OPERATION
                + SEPARATOR
                + buySum
                + System.lineSeparator()
                + RESULT_OPERATION
                + SEPARATOR
                + (supplySum - buySum);
        return result.trim();
    }
}
