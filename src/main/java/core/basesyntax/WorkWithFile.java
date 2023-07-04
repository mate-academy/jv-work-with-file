package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String OPERATION_TYPE_BUY = "buy";
    public static final String OPERATION_TYPE_SUPPLY = "supply";
    public static final String OPERATION_TYPE_RESULT = "result";
    public static final String COMMA_SIGN = ",";
    public static final String SPACE_SIGN = " ";
    public static final int CATEGORY_INDEX = 0;
    public static final int PRICE_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String textFromFile = readTextFromFile(fromFileName);
        String report = parseDataByCategory(textFromFile);
        writeTextFromFile(report, toFileName);
    }

    private String readTextFromFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(SPACE_SIGN);
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't open the file " + file.getName(), e);
        }
        return stringBuilder.toString();
    }

    private void writeTextFromFile(String textFromFile, String toFileName) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(textFromFile);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file " + file.getName(), e);
        }
    }

    private String parseDataByCategory(String data) {
        int supplySum = calculateSumByCategory(data, OPERATION_TYPE_SUPPLY);
        int buySum = calculateSumByCategory(data, OPERATION_TYPE_BUY);
        return getReport(supplySum, buySum);
    }

    private int calculateSumByCategory(String data, String categoryToCalculate) {
        int sum = 0;
        String[] generalSplitDataArray = data.split(SPACE_SIGN);
        for (int i = 0; i < generalSplitDataArray.length; i++) {
            String[] derivedSplitDataArray = generalSplitDataArray[i].split(COMMA_SIGN);
            String category = derivedSplitDataArray[CATEGORY_INDEX];
            if (category.equals(categoryToCalculate)) {
                int price = Integer.parseInt(derivedSplitDataArray[PRICE_INDEX]);
                sum += price;
            }
        }
        return sum;
    }

    private String getReport(int supplySum, int buySum) {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append(OPERATION_TYPE_SUPPLY)
                .append(COMMA_SIGN)
                .append(supplySum)
                .append(System.lineSeparator())
                .append(OPERATION_TYPE_BUY)
                .append(COMMA_SIGN)
                .append(buySum)
                .append(System.lineSeparator())
                .append(OPERATION_TYPE_RESULT)
                .append(COMMA_SIGN)
                .append(supplySum - buySum)
                .append(System.lineSeparator()).toString();
    }
}

