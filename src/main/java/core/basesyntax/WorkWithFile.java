package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String KEY_WORD_FOR_SUPPLY = "supply";
    public static final String KEY_WORD_FOR_BUY = "buy";
    public static final char CSV_SEPARATOR = ',';

    public void getStatistic(String fromFileName, String toFileName) {
        String fromFileString = readFromFile(fromFileName);
        String[] fromFileStrings = fromFileString.split(System.lineSeparator());
        int supplySum = 0;
        int buySum = 0;
        for (String string : fromFileStrings) {
            int amount = Integer.parseInt(string.substring(string.indexOf(CSV_SEPARATOR) + 1));
            supplySum += string.startsWith(KEY_WORD_FOR_SUPPLY) ? amount : 0;
            buySum += string.startsWith(KEY_WORD_FOR_BUY) ? amount : 0;
        }
        String toFileString = createReport(supplySum, buySum);
        writeToFile(toFileName, toFileString);
    }

    private String readFromFile(String fromFileName) {
        File fromFile = new File(fromFileName);
        StringBuilder stringBuilderFrom = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile))) {
            String valueFrom = bufferedReader.readLine();
            while (valueFrom != null) {
                stringBuilderFrom.append(valueFrom).append(System.lineSeparator());
                valueFrom = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file " + fromFileName, e);
        }
        return stringBuilderFrom.toString();
    }

    private String createReport(int supplySum, int buySum) {
        int result = supplySum - buySum;
        StringBuilder toFileStringBuilder = new StringBuilder();
        toFileStringBuilder.append(KEY_WORD_FOR_SUPPLY).append(CSV_SEPARATOR)
                .append(supplySum).append(System.lineSeparator())
                .append(KEY_WORD_FOR_BUY).append(CSV_SEPARATOR).append(buySum)
                .append(System.lineSeparator()).append("result")
                .append(CSV_SEPARATOR).append(result);
        return toFileStringBuilder.toString();
    }

    private void writeToFile(String toFileName, String dataToWrite) {
        File toFile = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            toFile.createNewFile();
            bufferedWriter.write(dataToWrite);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + toFileName, e);
        }
    }
}
