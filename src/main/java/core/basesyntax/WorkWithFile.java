package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String SUPPLY_PRODUCT = "supply";
    public static final String BUY_PRODUCT = "buy";
    public static final String RESULT = "result";
    public static final String COMMA = ",";
    public static final String SPLIT_CONST = "\\W+";
    public static final String LINE_CONST = System.lineSeparator();
    public static final int CONST = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(buildReport(readFile(fromFileName)), toFileName);
    }

    private String readFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        File file = new File(fromFileName);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            int value = reader.read();
            while (value != -1) {
                builder.append((char) value);
                value = reader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cant read file", e);
        }
        return builder.toString();
    }

    private String buildReport(String inPutParam) {
        int sumSupply = 0;
        int sumBuy = 0;
        String[] splitSentense = inPutParam.split(SPLIT_CONST);
        for (int i = 0; i < splitSentense.length; i++) {
            if (SUPPLY_PRODUCT.equals(splitSentense[i])) {
                sumSupply = sumSupply + Integer.parseInt(splitSentense[i + CONST]);
            } else if (BUY_PRODUCT.equals(splitSentense[i])) {
                sumBuy = sumBuy + Integer.parseInt(splitSentense[i + CONST]);
            }
        }
        StringBuilder builder2 = new StringBuilder();
        builder2.append(SUPPLY_PRODUCT).append(COMMA)
                .append(sumSupply)
                .append(LINE_CONST)
                .append(BUY_PRODUCT).append(COMMA)
                .append(sumBuy)
                .append(LINE_CONST)
                .append(RESULT).append(COMMA)
                .append(sumSupply - sumBuy);
        return builder2.toString();
    }

    private void writeToFile(String incomeStatistic, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, true))) {
            writer.write(incomeStatistic);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to \\'" + toFileName + "\\' file.", e);
        }
    }
}

