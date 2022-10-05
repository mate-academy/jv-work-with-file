package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    public static final String SUPPLY_PRODUCT = "supply";
    public static final String BUY_PRODUCT = "buy";
    public static final String RESULT = "result";
    public static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
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
        String sentence = builder.toString();
        int sumSupply = 0;
        int sumBuy = 0;
        String[] splitSentense = sentence.split("\\W+");
        for (int i = 0; i < splitSentense.length; i++) {
            if (SUPPLY_PRODUCT.equals(splitSentense[i])) {
                sumSupply = sumSupply + Integer.parseInt(splitSentense[i + 1]);
            }
            else if (BUY_PRODUCT.equals(splitSentense[i])) {
                sumBuy = sumBuy + Integer.parseInt(splitSentense[i + 1]);
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
                .append(sumSupply - sumBuy).toString();
        String statistic = builder2.toString();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, true))) {
            writer.write(statistic);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to \\'" + toFileName + "\\' file.", e);
        }
    }
}
