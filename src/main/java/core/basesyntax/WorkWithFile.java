package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String COMMA = ",";
    private static final String RESULT = "result";
    private static final int LENGTH_OF_WORD_SUPPLY = 7;
    private static final int LENGTH_OF_WORD_BUY = 4;

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        StringBuilder builder;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            builder = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        String[] resultSupplyBuy = builder.toString().split("\r\n");
        StringBuilder builderSupply = new StringBuilder();
        StringBuilder builderBuy = new StringBuilder();
        for (String test : resultSupplyBuy) {
            if (test.startsWith(SUPPLY)) {
                builderSupply.append(test.substring(LENGTH_OF_WORD_SUPPLY)).append(" ");
            }
            if (test.startsWith(BUY)) {
                builderBuy.append(test.substring(LENGTH_OF_WORD_BUY)).append(" ");
            }
        }
        String[] arrayStringSupply = builderSupply.toString().split(" ");
        int sumSupply = 0;
        for (String testStringSupply : arrayStringSupply) {
            sumSupply += Integer.parseInt(testStringSupply);
        }
        String[] arrayStringBuy = builderBuy.toString().split(" ");
        int sumBuy = 0;
        for (String testStringBuy : arrayStringBuy) {
            sumBuy += Integer.parseInt(testStringBuy);
        }
        int resultInt = sumSupply - sumBuy;
        File fileResult = new File(toFileName);
        StringBuilder stringBuilder;
        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new BufferedWriter(new FileWriter(fileResult, false)))) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(SUPPLY)
                    .append(COMMA)
                    .append(sumSupply)
                    .append("\r\n")
                    .append(BUY)
                    .append(COMMA)
                    .append(sumBuy)
                    .append("\r\n")
                    .append(RESULT)
                    .append(COMMA)
                    .append(resultInt);
            bufferedWriter.write(stringBuilder.toString());
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }
}
