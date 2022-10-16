package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static String ERROR_READER = "Can't read file";
    private static String WORD_DELI = ",";
    private static String WORD_BUY = "buy";
    private static String WORD_SUPPLY = "supply";
    private static String WORD_RESULT = "result,";
    private static String ERROR_WRITE = "Can't write data to file";
    private static String NEW_LINE = System.lineSeparator();
    private static int OPERATION_INDEX = 0;
    private static int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFromFile(fromFileName);
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

    public String getReport(String data) {
        String[] lines = data.split(NEW_LINE, 0);
        int sumBuy = 0;
        int sumSupply = 0;
        for (String line : lines) {
            String[] splitedLine = line.split(WORD_DELI);
            int sumWord = Integer.parseInt(splitedLine[AMOUNT_INDEX]);
            if (splitedLine[OPERATION_INDEX].equals(WORD_BUY)) {
                sumBuy += sumWord;
            } else {
                sumSupply += sumWord;
            }
        }
        StringBuilder builder = new StringBuilder();
        if (sumSupply != 0) {
            builder.append(WORD_SUPPLY).append(WORD_DELI).append(sumSupply).append(NEW_LINE);
        }
        if (sumBuy != 0) {
            builder.append(WORD_BUY).append(WORD_DELI).append(sumBuy).append(NEW_LINE);
        }
        builder.append(WORD_RESULT).append((sumSupply - sumBuy));
        return builder.toString();
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
