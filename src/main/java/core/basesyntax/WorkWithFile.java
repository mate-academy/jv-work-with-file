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

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        int countS = 0;
        int countB = 0;
        int sumS = 0;
        int sumB = 0;
        int difference;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            StringBuilder stringBuilder = new StringBuilder();
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append("\n");
                value = bufferedReader.readLine();
            }
            String stringBuilderToString = stringBuilder.toString();
            String[] stringBuilderToStringArray = stringBuilderToString.split("\n");
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
            for (int i = 0; i < stringBuilderToStringArray.length; i++) {
                if (stringBuilderToStringArray[i].charAt(0) == 's') {
                    int indexOf = stringBuilderToStringArray[i].indexOf(",");
                    stringBuilderToStringArray[i] =
                            stringBuilderToStringArray[i].substring(indexOf + 1);
                    sumS = sumS + Integer.parseInt(stringBuilderToStringArray[i]);

                }
                if (stringBuilderToStringArray[i].charAt(0) == 'b') {
                    int indexOf = stringBuilderToStringArray[i].indexOf(",");
                    stringBuilderToStringArray[i] =
                            stringBuilderToStringArray[i].substring(indexOf + 1);
                    sumB = sumB + Integer.parseInt(stringBuilderToStringArray[i]);
                }
            }
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
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }
    }
}
