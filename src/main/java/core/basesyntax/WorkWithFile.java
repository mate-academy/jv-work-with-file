package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int NAME_INDEX = 0;
    private static final int VALUE_INDEX = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final char COMMA = ',';

    public void getStatistic(String fromFileName, String toFileName) {
        int totalSupply = 0;
        int totalBuy = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                if (getName(value).equals(SUPPLY)) {
                    totalSupply += getAmount(value);
                }
                if (getName(value).equals(BUY)) {
                    totalBuy += getAmount(value);
                }
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("can't write to file");
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(buildInput(totalSupply, totalBuy));
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }

    private String getName(String lineFromFile) {
        return lineFromFile.split(",")[NAME_INDEX];
    }

    private int getAmount(String lineFromFile) {
        return Integer.parseInt(lineFromFile.split(",")[VALUE_INDEX]);
    }

    private String buildInput(int totalSupply, int totalBuy) {
        StringBuilder builder = new StringBuilder();
        builder.append(SUPPLY)
                .append(COMMA)
                .append(totalSupply)
                .append(System.lineSeparator())
                .append(BUY)
                .append(COMMA)
                .append(totalBuy)
                .append(System.lineSeparator())
                .append(RESULT)
                .append(COMMA)
                .append(totalSupply - totalBuy);
        return builder.toString();
    }
}
