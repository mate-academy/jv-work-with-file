package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int AMOUNT_INDEX = 1;
    private static final String SUPPLY_NAME = "supply";
    private static final String BUY_NAME = "buy";
    private static final String COMMA = ",";
    private static final String RESULT_NAME = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String inputCsv = fileReader(fromFileName);
        fileWriter(findSupplyAndBuy(inputCsv),toFileName);
    }

    private String findSupplyAndBuy(String text) {
        String result = "";
        int sumSupply = 0;
        int sumBuy = 0;
        String[] records = text.split(System.lineSeparator());
        for (String record : records) {
            String[] splitted = record.split(COMMA);
            if (record.contains(SUPPLY_NAME)) {
                sumSupply += Integer.parseInt(splitted[AMOUNT_INDEX]);
            } else {
                sumBuy += Integer.parseInt(splitted[AMOUNT_INDEX]);
            }
        }
        result += SUPPLY_NAME + COMMA + sumSupply + System.lineSeparator();
        result += BUY_NAME + COMMA + sumBuy + System.lineSeparator();
        result += RESULT_NAME + COMMA + (sumSupply - sumBuy);
        return result;
    }

    private String fileReader(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return stringBuilder.toString();
    }

    private void fileWriter(String result, String toFileName) {
        File output = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(output))) {
            bufferedWriter.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data",e);
        }
    }
}
