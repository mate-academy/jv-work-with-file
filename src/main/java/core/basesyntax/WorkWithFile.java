package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class WorkWithFile {
    private static String ERROR_READER = "Can't read file";
    private static String WORD_DELI = ",";
    private static String WORD_BUY = "buy";
    private static String WORD_SUPPLY = "supply";
    private static String WORD_RESULT = "result,";
    private static String ERROR_WRITE = "Can't write data to file";
    private static String NEW_LINE = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) {
        int count = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            String value = reader.readLine();
            while (value != null) {
                if (value.length() > 0) {
                    count++;
                }
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(ERROR_READER, e);
        }
        if (count == 0) {
            File file = new File(toFileName);
            return;
        }
        String[] arLines = new String[count];
        int i = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            String value = reader.readLine();
            while (value != null) {
                if (value.length() > 0) {
                    arLines[i] = value;
                    i++;
                }
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(ERROR_READER, e);
        }
        Arrays.sort(arLines, Comparator.naturalOrder());
        int sumBuy = 0;
        int sumSupply = 0;
        for (String line : arLines) {
            String[] arLine = line.split(WORD_DELI, 0);
            int sumWord = Integer.parseInt(arLine[1]);
            if (arLine[0].equals(WORD_BUY)) {
                sumBuy += sumWord;
            } else {
                sumSupply += sumWord;
            }
        }
        String result = WORD_SUPPLY + "," + sumSupply + NEW_LINE
                + WORD_BUY + "," + sumBuy + NEW_LINE
                + WORD_RESULT + (sumSupply - sumBuy);
        File fileTo = new File(toFileName);
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileTo, true));
            bufferedWriter.write(result);
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(ERROR_WRITE, e);
        }
    }
}
