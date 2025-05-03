package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_INDEX = 0;
    private static final int VALUE_INDEX = 1;
    private static final String SUPPLY_OPERATION = "supply";
    private static final String BUY_OPERATION = "buy";
    private static final String RESULT_NAME = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromFile = readFromFile(fromFileName);
        String report = updateData(dataFromFile);
        writeToFile(toFileName, report);
    }

    private String[] readFromFile(String filePath) {
        StringBuilder data = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            while (line != null) {
                data.append(line).append(System.lineSeparator());
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + filePath, e);
        }
        return data.toString().split(System.lineSeparator());
    }

    private String updateData(String[] data) {
        StringBuilder resultString = new StringBuilder();
        int supplySum = 0;
        int buySum = 0;

        for (String line : data) {
            String[] action = line.split(",");
            if (action[OPERATION_INDEX].equals(SUPPLY_OPERATION)) {
                supplySum += Integer.parseInt(action[VALUE_INDEX]);
            }
            if (action[OPERATION_INDEX].equals(BUY_OPERATION)) {
                buySum += Integer.parseInt(action[VALUE_INDEX]);
            }
        }
        int result = supplySum - buySum;
        return resultString.append(SUPPLY_OPERATION + ",").append(supplySum)
                .append(System.lineSeparator())
                .append(BUY_OPERATION + ",").append(buySum)
                .append(System.lineSeparator())
                .append(RESULT_NAME + ",").append(result).toString();
    }

    private void writeToFile(String filePath, String result) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
            bufferedWriter.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + filePath, e);
        }
    }
}

