package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMMA = ",";
    private static final String WORD_BUY = "buy";
    private static final String WORD_SUPPLY = "supply";
    private static final String WORD_RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String stringFromFile = readFromFile(fromFileName);
        String result = createReport(stringFromFile);
        writeToFile(toFileName, result);
    }

    private String createReport(String infoFromFile) {
        String[] information = infoFromFile.split(COMMA);
        StringBuilder result = new StringBuilder();
        int sumOfBuy = 0;
        int sumOfSupply = 0;
        for (int i = 0; i < information.length - 1; i += 2) {
            sumOfBuy += information[i].equals(WORD_BUY)
                    ? Integer.parseInt(information[i + 1]) : 0;
            sumOfSupply += information[i].equals(WORD_SUPPLY)
                    ? Integer.parseInt(information[i + 1]) : 0;
        }
        result.append(WORD_SUPPLY).append(COMMA)
                .append(sumOfSupply)
                .append(System.lineSeparator())
                .append(WORD_BUY).append(COMMA)
                .append(sumOfBuy)
                .append(System.lineSeparator())
                .append(WORD_RESULT).append(COMMA)
                .append(sumOfSupply - sumOfBuy);
        return result.toString();
    }

    private String readFromFile(String fileName) {
        StringBuilder stringFromFile = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String value = bufferedReader.readLine();
            while (value != null) {
                stringFromFile.append(value).append(COMMA);
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fileName, e);
        }
        return stringFromFile.toString();
    }

    private void writeToFile(String fileName, String content) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(content);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + fileName, e);
        }
    }
}
