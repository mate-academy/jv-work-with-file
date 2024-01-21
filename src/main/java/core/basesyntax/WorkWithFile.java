package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String[] STATISTIC = {"supply", "buy"};
    private static final String SEPARATOR = ",";
    private static final String RESULT = "result";
    private static final int AMOUNT_SUPPLY_INDEX = 0;
    private static final int AMOUNT_BUY_INDEX = 1;
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readeFromFile(fromFileName);
        String result = createResultReport(dataFromFile);
        writeToFile(toFileName, result);
    }

    private String readeFromFile(String filename) {
        File file = new File(filename);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String oneLine = reader.readLine();
            while (oneLine != null) {
                stringBuilder.append(oneLine).append(System.lineSeparator());
                oneLine = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return stringBuilder.toString();
    }

    private String createResultReport(String fileString) {
        String[] lines = fileString.split(System.lineSeparator());
        int[] amount = {0, 0};
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < STATISTIC.length; i++) {
            for (String line : lines) {
                String[] splitLine = line.split(SEPARATOR);
                if (splitLine[OPERATION_TYPE_INDEX].equals(STATISTIC[i])) {
                    amount[i] += Integer.parseInt(splitLine[AMOUNT_INDEX]);
                }
            }
            stringBuilder.append(STATISTIC[i]).append(SEPARATOR);
            stringBuilder.append(amount[i]).append(System.lineSeparator());
        }
        stringBuilder.append(RESULT).append(SEPARATOR);
        stringBuilder.append(amount[AMOUNT_SUPPLY_INDEX] - amount[AMOUNT_BUY_INDEX]);
        return stringBuilder.toString();
    }

    private void writeToFile(String fileName, String result) {
        File file = new File(fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
