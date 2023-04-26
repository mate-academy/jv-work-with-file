package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";
    private static final int INDEX_ONE = 1;
    private static final int INDEX_ZERO = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] infoFromFileName = readFile(fromFileName);
        String reportSupplyAndBuy = getReport(infoFromFileName);
        writeFile(toFileName, reportSupplyAndBuy);
    }

    private String[] readFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file - " + fileName, e);
        }
        return stringBuilder.toString().split(System.lineSeparator());
    }

    private String getReport(String[] information) {
        int sumSupply = INDEX_ZERO;
        int sumBuy = INDEX_ZERO;
        for (String sentence : information) {
            String[] word = sentence.split(COMMA);
            if (word[0].equals(SUPPLY)) {
                sumSupply += Integer.parseInt(word[INDEX_ONE]);
            } else {
                sumBuy += Integer.parseInt(word[INDEX_ONE]);
            }
        }
        return (SUPPLY) + (COMMA) + (sumSupply) + (System.lineSeparator())
                + (BUY) + (COMMA) + (sumBuy) + (System.lineSeparator())
                + (RESULT) + (COMMA) + (sumSupply - sumBuy);
    }

    private void writeFile(String fileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file - " + fileName, e);
        }
    }
}
