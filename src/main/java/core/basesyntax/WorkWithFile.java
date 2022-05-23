package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String[] namesOfAction = new String[] {"supply", "buy"};
    private static final int NUMBER_OF_ACTIONS = 2;
    private static final int ACTION_SUPPLY = 0;
    private static final int ACTION_BUY = 1;
    private static final String COMMA = ",";
    private static final int AMMOUNT = 1;
    private static final String RESULT_SUM_OF_ACTIONS = "result,";

    public void getStatistic(String fromFileName, String toFileName) {
        String inputData = this.readFile(fromFileName);
        String dataToWrite = calculateData(inputData);
        writeFile(dataToWrite, toFileName);
    }

    private void writeFile(String outputData, String toFileName) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(outputData);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file " + toFileName, e);
        }
    }

    private String calculateData(String inputData) {
        String[] values = new String[NUMBER_OF_ACTIONS];
        int[] sumOfRecords = new int[NUMBER_OF_ACTIONS];
        String[] records = inputData.split(System.lineSeparator());
        StringBuilder calculatedData = new StringBuilder();
        for (int j = 0; j < records.length; j++) {
            String[] record = records[j].split(COMMA);
            if (namesOfAction[ACTION_SUPPLY].equals(record[ACTION_SUPPLY])) {
                sumOfRecords[ACTION_SUPPLY] += Integer.parseInt(record[AMMOUNT]);
            }
            if (namesOfAction[ACTION_BUY].equals(record[ACTION_SUPPLY])) {
                sumOfRecords[ACTION_BUY] += Integer.parseInt(record[AMMOUNT]);
            }
        }
        calculatedData.append(namesOfAction[ACTION_SUPPLY]).append(COMMA).append(sumOfRecords[ACTION_SUPPLY])
                .append(System.lineSeparator());
        calculatedData.append(namesOfAction[ACTION_BUY]).append(COMMA).append(sumOfRecords[ACTION_BUY])
                .append(System.lineSeparator());
        calculatedData.append(RESULT_SUM_OF_ACTIONS).append(sumOfRecords[ACTION_SUPPLY] - sumOfRecords[ACTION_BUY]);
        return calculatedData.toString();
    }

    private String readFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fromFileName, e);
        }
        return builder.toString();
    }
}
