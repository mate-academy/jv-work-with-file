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
        writeToFilee(doNeededForm(getSumValue(fromFileName)),toFileName);
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

    private int [] getSumValue(String adres) {
        int[] sumArray = new int[2];
        String[] array = readFromFile(adres).split(System.lineSeparator());
        for (int i = ZERO_VALUE; i < array.length; i++) {
            if (array[i].startsWith("supply")) {
                sumArray[0] += Integer.parseInt(array[i].split(",")[SECOND_POSITION]);
            }
            if (array[i].startsWith("buy")) {
                sumArray[1] += Integer.parseInt(array[i].split(",")[SECOND_POSITION]);
            }
        }
        return sumArray;
    }

    private String doNeededForm(int [] array) {
        return new StringBuilder().append("supply,").append(String.valueOf(array[0]))
         .append(System.lineSeparator())
          .append("buy,").append(String.valueOf(array[1])).append(System.lineSeparator())
         .append("result,").append(String.valueOf(array[0] - array[1])).toString();
    }

    private void writeToFilee(String content,String to) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(to))) {
            bufferedWriter.write(content);
        } catch (IOException e) {
            throw new RuntimeException("Can not write data to file",e);
        }
    }
}


