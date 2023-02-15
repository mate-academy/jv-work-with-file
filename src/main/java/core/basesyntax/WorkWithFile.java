package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String DELIMITER = "///";
    private static final int POSITION_OF_TITLE = 0;
    private static final int POSITION_OF_VALUE = 1;
    private static final int POSITION_OF_SUPPLY_VALUE = 0;
    private static final int POSITION_OF_BUY_VALUE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String text = readFile(fromFileName);
        text = createReport(text);
        putDataToFile(text,toFileName);
    }

    private String readFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String text = bufferedReader.readLine();
            while (text != null) {
                stringBuilder.append(text).append(DELIMITER);
                text = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t open file!" + fileName, e);
        }
        if (stringBuilder.toString().isEmpty()) {
            String empty = new String();
            return empty;
        }
        return stringBuilder.toString();
    }

    /* methods calculateDataForReport return int array with two elements.
    * first element mean sum of all values with supply
    * second element mean sum of all values with buy */
    private int[] calculateDataForReport(String[] str) {
        int[] result = new int[]{0, 0};
        for (int i = 0; i < str.length; i++) {
            String[] strings = str[i].split(",");
            if (strings[POSITION_OF_TITLE].equals("supply")) {
                result[POSITION_OF_SUPPLY_VALUE] += Integer.parseInt(strings[POSITION_OF_VALUE]);
            } else if (strings[POSITION_OF_TITLE].equals("buy")) {
                result[POSITION_OF_BUY_VALUE] += Integer.parseInt(strings[POSITION_OF_VALUE]);
            }
        }
        return result;
    }

    private String createReport(String string) {
        int[] data = calculateDataForReport(string.split(DELIMITER));
        StringBuilder result = new StringBuilder();
        result.append("supply,").append(data[0]).append("\n");
        result.append("buy,").append(data[1]).append("\n");
        result.append("result,").append(data[0] - data[1]).append("\n");
        return result.toString();
    }

    private void putDataToFile(String text, String putFile) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(putFile))) {
            bufferedWriter.write(text);
        } catch (IOException e) {
            throw new RuntimeException("Can`t put data to file!" + putFile,e);
        }
    }
}
