package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_CATEGORY = "supply";
    private static final String BUY_CATEGORY = "buy";
    private static final String SPLIT_VALUE = ",";
    private static final String RESULT_CATEGORY = "result";
    private static final int FIRST_VALUE = 0;
    private static final int SECOND_VALUE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        writeInFile(getResult(readFile(fromFileName).toString()), toFileName);
    }

    private StringBuilder readFile(String fromFileName) {
        StringBuilder readFile = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                readFile.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("The file at path" + fromFileName + " was not read : " + e);
        }
        return readFile;
    }

    private String getResult(String fileContent) {
        String[] readFileToStringArray = fileContent.split(System.lineSeparator());
        int sumSupply = 0;
        int sumBuy = 0;
        for (String oneLine : readFileToStringArray) {
            String[] oneCategoryAndPrice = oneLine.split(SPLIT_VALUE);
            if (oneCategoryAndPrice[FIRST_VALUE].equals(SUPPLY_CATEGORY)) {
                sumSupply += Integer.parseInt(oneCategoryAndPrice[SECOND_VALUE]);
            } else if (oneCategoryAndPrice[FIRST_VALUE].equals(BUY_CATEGORY)) {
                sumBuy += Integer.parseInt(oneCategoryAndPrice[SECOND_VALUE]);
            }
        }
        return createResultString(sumSupply, sumBuy);
    }

    private String createResultString(int sumSupply, int sumBuy) {
        StringBuilder result = new StringBuilder();
        result.append(SUPPLY_CATEGORY)
                .append(SPLIT_VALUE)
                .append(sumSupply)
                .append(System.lineSeparator())
                .append(BUY_CATEGORY)
                .append(SPLIT_VALUE)
                .append(sumBuy)
                .append(System.lineSeparator())
                .append(RESULT_CATEGORY)
                .append(SPLIT_VALUE)
                .append(sumSupply - sumBuy);
        return result.toString();
    }

    private void writeInFile(String data, String fileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("The file at path " + fileName + " was not written" + e);
        }
    }
}
