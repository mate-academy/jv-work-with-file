package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    static final int INDEX_ARRAY_FROM_THE_NUMBER = 1;
    static final String SEARCH_NAME = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        String inputCsv = fileReader(fromFileName);
        fileWriter(findSupplyAndBuy(inputCsv),toFileName);
    }

    private String findSupplyAndBuy(String text) {
        String result = "";
        int sumSupply = 0;
        int sumBuy = 0;
        String[] arrayFullText = text.split("\r\n");
        for (String lineArray : arrayFullText) {
            String[] line = lineArray.split(",");
            if (lineArray.contains(SEARCH_NAME)) {
                sumSupply += Integer.parseInt(line[INDEX_ARRAY_FROM_THE_NUMBER]);
            } else {
                sumBuy += Integer.parseInt(line[INDEX_ARRAY_FROM_THE_NUMBER]);
            }
        }
        result += "supply," + sumSupply + System.lineSeparator();
        result += "buy," + sumBuy + System.lineSeparator();
        result += "result," + (sumSupply - sumBuy);
        return result;
    }

    private String fileReader(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
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

    private void fileWriter(String result,String toFileName) {
        File output = new File(toFileName);
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(output));
            bufferedWriter.write(result);
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't write data",e);
        }
    }
}
