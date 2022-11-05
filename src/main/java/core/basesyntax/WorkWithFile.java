package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int FIRST_INDEX_IN_ARRAY = 0;
    private static final int SECOND_INDEX_IN_ARRAY = 1;
    private static final int OPERATION = 0;
    private static final int AMOUNT = 1;
    private static final String REGEX = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String readText = readTheFile(fromFileName);
        String resultData = createReport(readText);
        writeTheFile(toFileName, resultData);
    }

    private String readTheFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("can`t read the file" + fromFileName, e);
        }
        return stringBuilder.toString();
    }

    private void writeTheFile(String toFileName, String resultData) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.write(resultData);
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException("can`t write to file" + toFileName, e);
        }
    }

    private String createReport(String readText) {
        int[] count = countSum(readText);
        int sumSupply = count[FIRST_INDEX_IN_ARRAY];
        int sumBuy = count[SECOND_INDEX_IN_ARRAY];
        StringBuilder resultData = new StringBuilder().append(SUPPLY).append(REGEX)
                .append(sumSupply).append(System.lineSeparator()).append(BUY)
                .append(REGEX).append(sumBuy).append(System.lineSeparator())
                .append(RESULT).append(REGEX).append(sumSupply - sumBuy);
        return resultData.toString();
    }

    private int[] countSum(String readText) {
        int sumSupply = 0;
        int sumBuy = 0;
        String[] splitArray = readText.split(System.lineSeparator());
        for (String operationTypeAndValue : splitArray) {
            String[] insideOperation = operationTypeAndValue.split(REGEX);
            switch (insideOperation[OPERATION]) {
                case SUPPLY:
                    sumSupply += Integer.parseInt(insideOperation[AMOUNT]);
                    break;
                case BUY:
                default:
                    sumBuy += Integer.parseInt(insideOperation[AMOUNT]);
                    break;
            }
        }
        return new int[]{sumSupply, sumBuy};
    }
}
