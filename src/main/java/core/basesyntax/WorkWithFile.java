package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION = 0;
    private static final int AMOUNT = 1;
    private static final String REGEX = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder stringBuilder = readTheFile(fromFileName);
        String resultData = createReport(stringBuilder);
        writeTheFile(toFileName, resultData);
    }

    private StringBuilder readTheFile(String fromFileName) {
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
        return stringBuilder;
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

    private String createReport(StringBuilder stringBuilder) {
        int[] count = countSum(stringBuilder);
        int sumSupply = count[0];
        int sumBuy = count[1];
        StringBuilder resultData = new StringBuilder().append(SUPPLY).append(REGEX)
                .append(sumSupply).append(System.lineSeparator()).append(BUY)
                .append(REGEX).append(sumBuy).append(System.lineSeparator())
                .append(RESULT).append(REGEX).append(Integer.valueOf(sumSupply - sumBuy));
        return resultData.toString();
    }

    private int[] countSum(StringBuilder stringBuilder) {
        int sumSupply = 0;
        int sumBuy = 0;
        String[] splitArray = stringBuilder.toString().split(System.lineSeparator());
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
