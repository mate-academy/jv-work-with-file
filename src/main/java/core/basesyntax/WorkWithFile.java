package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int TRANSACTION_TYPE_INDEX = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String COMMA = ",";
    private static final String RESULT = "result";
    private static final String NON_WORD_CHARACTERS = "\\W+";

    public void getStatistic(String fromFileName, String toFileName) {
        writeTextToFile(
                createReport(getTextFromFile(fromFileName)),
                toFileName);
    }

    private void writeTextToFile(String data, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName, e);
        }
    }

    private String getTextFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();

            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }

        return stringBuilder.toString().toLowerCase();
    }

    private String createReport(String text) {
        int supplyTotal = 0;
        int buyTotal = 0;
        int total;
        StringBuilder stringBuilder = new StringBuilder();
        String [] table = text.split(NON_WORD_CHARACTERS);
        for (int i = 0; i < table.length; i++) {
            if (table[i].equals(SUPPLY)) {
                supplyTotal += Integer.parseInt(table[i + TRANSACTION_TYPE_INDEX]);
            } else if (table[i].equals(BUY)) {
                buyTotal += Integer.parseInt(table[i + TRANSACTION_TYPE_INDEX]);
            }
        }
        total = supplyTotal - buyTotal;
        stringBuilder
                .append(SUPPLY)
                .append(COMMA)
                .append(supplyTotal)
                .append(System.lineSeparator())
                .append(BUY)
                .append(COMMA)
                .append(buyTotal)
                .append(System.lineSeparator())
                .append(RESULT)
                .append(COMMA)
                .append(total)
                .append(System.lineSeparator());
        return stringBuilder.toString();
    }
}
