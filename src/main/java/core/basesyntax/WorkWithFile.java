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

    public void getStatistic(String fromFileName, String toFileName) {
        String textFromFile = readTextFromFile(fromFileName);
        writeTextFromFile(textFromFile, toFileName);
    }

    private String readTextFromFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        String result;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(" ");
                value = bufferedReader.readLine();
            }
            result = getTotalAmount(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't open the file " + file.getName(), e);
        }
        return result;
    }

    private void writeTextFromFile(String textFromFile, String toFileName) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(textFromFile);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file " + file.getName(), e);
        }
    }

    private String getTotalAmount(String data) {
        int supplySum = 0;
        int buySum = 0;
        StringBuilder stringBuilder = new StringBuilder();
        String[] generalSplitDataArray = data.split(" ");
        for (int i = 0; i < generalSplitDataArray.length; i++) {
            String[] derivedSplitDataArray = generalSplitDataArray[i].split(",");
            String category = derivedSplitDataArray[0];
            int price = Integer.parseInt(derivedSplitDataArray[1]);
            if (category.equals(OPERATION_TYPE_SUPPLY)) {
                supplySum += price;
            } else if (category.equals(OPERATION_TYPE_BUY)) {
                buySum += price;
            }
        }
        return stringBuilder.append(OPERATION_TYPE_SUPPLY)
                .append(",")
                .append(supplySum)
                .append(System.lineSeparator())
                .append(OPERATION_TYPE_BUY)
                .append(",")
                .append(buySum)
                .append(System.lineSeparator())
                .append(OPERATION_TYPE_RESULT)
                .append(",")
                .append(supplySum - buySum)
                .append(System.lineSeparator()).toString();
    }
}

