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
    private StringBuilder stringBuilder;
    private File file;
    private int sumSupply = 0;
    private int sumBuy = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        readTheFile(fromFileName);
        countSum();
        writeTheFile(toFileName);
    }

    private void readTheFile(String fromFileName) {
        file = new File(fromFileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            stringBuilder = new StringBuilder();
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("can`t read the file" + fromFileName, e);
        }
    }

    private void writeTheFile(String toFileName) {
        file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            stringBuilder = new StringBuilder().append(SUPPLY).append(REGEX).append(sumSupply)
                            .append(System.lineSeparator()).append(BUY).append(REGEX).append(sumBuy)
                            .append(System.lineSeparator()).append(RESULT).append(REGEX)
                            .append(Integer.valueOf(sumSupply - sumBuy));
            bufferedWriter.write(stringBuilder.toString());
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException("can`t write to file" + toFileName, e);
        }
    }

    private void countSum() {
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
    }
}
