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
    private static final String NEW_LINE = System.lineSeparator();
    private static final String SUPPLY = "supply,";
    private static final String BUY = "buy,";
    private static final String RESULT_WORD = "result,";
    private int sumS = 0;
    private int sumB = 0;

    private String readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(NEW_LINE);
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }
        return stringBuilder.toString();
    }

    private void fillAllArray(String[] sumBuyAndSupply) {
        for (int i = 0; i < sumBuyAndSupply.length; i++) {
            String[] split = sumBuyAndSupply[i].split(",");
            if (split[0].charAt(0) == 's') {
                sumS = sumS + Integer.parseInt(split[1]);
            }
            if (split[0].charAt(0) == 'b') {
                sumB = sumB + Integer.parseInt(split[1]);
            }
        }
    }

    private void writeToFile(String toFileName) {
        int difference = sumS - sumB;
        RESULT[0] = SUPPLY + sumS;
        RESULT[1] = BUY + sumB;
        RESULT[2] = RESULT_WORD + difference;
        File toFile = new File(toFileName);
        for (String res : RESULT) {
            try (BufferedWriter bufferedWriter =
                         new BufferedWriter(new FileWriter(toFile, true))) {
                bufferedWriter.write(res);
                bufferedWriter.write(NEW_LINE);
            } catch (IOException e) {
                throw new RuntimeException("Can`t write data file", e);
            }
        }
    }

    public void getStatistic(String fromFileName, String toFileName) {
        String stringBuilderToString = readFromFile(fromFileName);
        String[] stringBuilderToStringArray = stringBuilderToString.split(System.lineSeparator());
        fillAllArray(stringBuilderToStringArray);
        writeToFile(toFileName);
    }
}

