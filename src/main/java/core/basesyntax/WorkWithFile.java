package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR_SYMBOL = ",";
    private static final int OPERATION_INDEX = 0;
    private static final int SUM_INDEX = 1;
    private static final String SUPPLY_STRING = "supply";
    private static final String BUY_STRING = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFromFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(report, toFileName);
    }

    private String readFromFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file with name: " + fileName, e);
        }
        return stringBuilder.toString();
    }

    private String createReport(String data) {
        int supplySum = 0;
        int buySum = 0;
        String[] splitedData = data.split(System.lineSeparator());
        for (String line : splitedData) {
            String[] splitedLine = line.split(SEPARATOR_SYMBOL);
            if (splitedLine[OPERATION_INDEX].equals(SUPPLY_STRING)) {
                supplySum += Integer.parseInt(splitedLine[SUM_INDEX]);
            }
            if (splitedLine[OPERATION_INDEX].equals(BUY_STRING)) {
                buySum += Integer.parseInt(splitedLine[SUM_INDEX]);
            }
        }
        return new StringBuilder(SUPPLY_STRING)
                .append(SEPARATOR_SYMBOL).append(supplySum)
                .append(System.lineSeparator()).append(BUY_STRING)
                .append(SEPARATOR_SYMBOL).append(buySum)
                .append(System.lineSeparator()).append("result")
                .append(SEPARATOR_SYMBOL).append(supplySum - buySum).toString();
    }

    private void writeToFile(String report, String fileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, true))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can’t write data to file", e);
        }
    }
}
