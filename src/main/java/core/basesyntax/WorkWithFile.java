package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int SIZE_OF_RESULT_ARRAY = 3;
    private static final String[] RESULT = new String[SIZE_OF_RESULT_ARRAY];
    private int countS = 0;
    private int countB = 0;
    private int sumS = 0;
    private int sumB = 0;
    private int difference;

    private String readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append("\n");
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }
        return stringBuilder.toString();
    }

    private String[] writeSupplyAndBuyToArray(String[] stringBuilderToStringArray) {
        for (int i = 0; i < stringBuilderToStringArray.length; i++) {
            if (stringBuilderToStringArray[i].charAt(0) == 's') {
                countS++;
                if (countS == 1) {
                    int index = stringBuilderToStringArray[i].indexOf(",");
                    RESULT[0] = stringBuilderToStringArray[i].substring(0, index + 1);
                }
            }
            if (stringBuilderToStringArray[i].charAt(0) == 'b') {
                countB++;
                if (countB == 1) {
                    int index = stringBuilderToStringArray[i].indexOf(",");
                    RESULT[1] = stringBuilderToStringArray[i].substring(0, index + 1);
                }
            }
        }
        return RESULT;
    }

    private void fillAllArray(String[] writeSupplyAndBuyToArray) {
        for (int i = 0; i < writeSupplyAndBuyToArray.length; i++) {
            if (writeSupplyAndBuyToArray[i].charAt(0) == 's') {
                int indexOf = writeSupplyAndBuyToArray[i].indexOf(",");
                writeSupplyAndBuyToArray[i] =
                        writeSupplyAndBuyToArray[i].substring(indexOf + 1);
                sumS = sumS + Integer.parseInt(writeSupplyAndBuyToArray[i]);

            }
            if (writeSupplyAndBuyToArray[i].charAt(0) == 'b') {
                int indexOf = writeSupplyAndBuyToArray[i].indexOf(",");
                writeSupplyAndBuyToArray[i] =
                        writeSupplyAndBuyToArray[i].substring(indexOf + 1);
                sumB = sumB + Integer.parseInt(writeSupplyAndBuyToArray[i]);
            }
        }
    }

    private void writeToFile(String toFileName) {
        difference = sumS - sumB;
        RESULT[0] = RESULT[0] + sumS;
        RESULT[1] = RESULT[1] + sumB;
        RESULT[2] = "result," + difference;
        File toFile = new File(toFileName);
        for (String res : RESULT) {
            try {
                BufferedWriter bufferedWriter =
                        new BufferedWriter(new FileWriter(toFile, true));
                bufferedWriter.write(res);
                bufferedWriter.write(System.lineSeparator());
                bufferedWriter.close();
            } catch (IOException e) {
                throw new RuntimeException("Can`t write data file", e);
            }
        }
    }

    public void getStatistic(String fromFileName, String toFileName) {
        String stringBuilderToString = readFromFile(fromFileName);
        String[] stringBuilderToStringArray = stringBuilderToString.split("\n");
        String[] writeSupplyAndBuyToArray = writeSupplyAndBuyToArray(stringBuilderToStringArray);
        fillAllArray(stringBuilderToStringArray);
        writeToFile(toFileName);
    }
}

