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

    public void getStatistic(String fromFileName, String toFileName) {
        writeTextToFile(
                createReport(getTextFromFile(fromFileName)),
                toFileName);
    }

    private void writeTextToFile(String data, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file ", e);
        }
    }

    private String getTextFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            String value = bufferedReader.readLine();

            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException("Can't read file ", e);
        }

        return stringBuilder.toString().toLowerCase();
    }

    private String createReport(String text) {
        StringBuilder stringBuilder = new StringBuilder();
        int supplyTotal = 0;
        int buyTotal = 0;
        int result = 0;
        String [] table = text.split("\\W+");
        for (int i = 0; i < table.length; i++) {
            if (table[i].contains(SUPPLY)) {
                supplyTotal += Integer.parseInt(table[i + TRANSACTION_TYPE_INDEX]);
            } else if (table[i].contains(BUY)) {
                buyTotal += Integer.parseInt(table[i + TRANSACTION_TYPE_INDEX]);
            }
        }
        result = supplyTotal - buyTotal;
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
                .append(result)
                .append(System.lineSeparator());
        return stringBuilder.toString();
    }
}
