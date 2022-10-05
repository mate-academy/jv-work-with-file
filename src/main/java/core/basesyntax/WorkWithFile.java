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
    public static final int CONST = 1;

    public void getStatistic(String fromFileName, String toFileName) {

    }

    public String readFromFile(File file) {
        StringBuilder builder = new StringBuilder();

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
        String sentence = builder.toString();
        return sentence;
    }

    private String generateStatistic(String[]) {
        int sumSupply = 0;
        int sumBuy = 0;
        String[] splitSentense = readFromFile().split(SPLIT_CONST);
        for (int i = 0; i < splitSentense.length; i++) {
            if (SUPPLY_PRODUCT.equals(splitSentense[i])) {
                sumSupply = sumSupply + Integer.parseInt(splitSentense[i + CONST]);
            }
            else if (BUY_PRODUCT.equals(splitSentense[i])) {
                sumBuy = sumBuy + Integer.parseInt(splitSentense[i + CONST]);
            }
        }
        StringBuilder builder2 = new StringBuilder();
        builder2.append(SUPPLY_PRODUCT).append(COMMA)
                .append(sumSupply)
                .append(System.lineSeparator())
                .append(BUY_PRODUCT).append(COMMA)
                .append(sumBuy)
                .append(System.lineSeparator())
                .append(RESULT).append(COMMA)
                .append(sumSupply - sumBuy);
        return builder2.toString();
    }

    private void writeToFile(String toFileName, String statistic) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, true))) {
            writer.write(statistic);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to \\'" + toFileName + "\\' file.", e);
        }
    }
}

