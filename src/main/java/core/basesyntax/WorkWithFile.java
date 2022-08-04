package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int ZERO_VALUE = 0;
    private static final int SECOND_POSITION = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        readFromFile(fromFileName);
        writeToFilee(doNeedForm(fromFileName),toFileName);
    }

    private String readFromFile(String adres) {
        File file = new File(adres);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String result = stringBuilder.toString();
        return result;
    }

    private String doNeedForm(String adres) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] array = readFromFile(adres).split(System.lineSeparator());
        int sumOfSupply = ZERO_VALUE;
        int sumOfBuy = ZERO_VALUE;
        for (int i = ZERO_VALUE; i < array.length; i++) {
            if (array[i].startsWith("supply")) {
                sumOfSupply += Integer.parseInt(array[i].split(",")[SECOND_POSITION]);
            }
            if (array[i].startsWith("buy")) {
                sumOfBuy += Integer.parseInt(array[i].split(",")[SECOND_POSITION]);
            }
        }
        return stringBuilder.append("supply,").append(String.valueOf(sumOfSupply))
         .append(System.lineSeparator())
          .append("buy,").append(String.valueOf(sumOfBuy)).append(System.lineSeparator())
         .append("result,").append(String.valueOf(sumOfSupply - sumOfBuy)).toString();
    }

    private void writeToFilee(String content,String to) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(to))) {
            bufferedWriter.write(content);
        } catch (IOException e) {
            throw new RuntimeException("Can not write data to file",e);
        }
    }
}


