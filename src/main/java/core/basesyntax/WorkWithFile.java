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
        String data = readFromFile(fromFileName);
        if (data.length() == 0) {
            File file = new File(toFileName);
            return;
        }
        String report = getReport(data);
        writeToFile(report, toFileName);
    }

    public String readFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            String value = bufferedReader.readLine();
            while (value != null) {
                if (value.length() > 0) {
                    stringBuilder.append(value).append(NEW_LINE);
                }
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(ERROR_READER, e);
        }
        return stringBuilder.toString();
    }

    public String getReport(String strData) {
        String[] arLines = strData.split(NEW_LINE, 0);
        Arrays.sort(arLines, Comparator.naturalOrder());
        int sumBuy = 0;
        int sumSupply = 0;
        for (String line : arLines) {
            String[] arWords = line.split(WORD_DELI, 0);
            int sumWord = Integer.parseInt(arWords[1]);
            if (arWords[0].equals(WORD_BUY)) {
                sumBuy += sumWord;
            } else {
                sumSupply += sumWord;
            }
        }
        String result = "";
        if (sumSupply != 0) {
            result += WORD_SUPPLY + "," + sumSupply + NEW_LINE;
        }
        if (sumBuy != 0) {
            result += WORD_BUY + "," + sumBuy + NEW_LINE;
        }
        result += WORD_RESULT + (sumSupply - sumBuy);
        return result;
    }

    public void writeToFile(String strReport, String toFileName) {
        File fileTo = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileTo, true))) {
            bufferedWriter.write(strReport);
        } catch (IOException e) {
            throw new RuntimeException(ERROR_WRITE);
        }
    }
}
