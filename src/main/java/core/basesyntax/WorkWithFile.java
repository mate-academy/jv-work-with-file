package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static int WORD_PART = 0;
    private static int VALUE_PART = 1;
    private static String SUPPLY = "supply";
    private static String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        int totalBuy = 0;
        int totalSupply = 0;
        File file = new File(fromFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            List<String> strings = Files.readAllLines(file.toPath());
            for (String string: strings) {
                String[] stringParts = string.split(",");
                String word = stringParts[WORD_PART];
                String value = stringParts[VALUE_PART];
                if (SUPPLY.equals(word)) {
                    totalSupply += Integer.parseInt(value);
                } else if (BUY.equals(word)) {
                    totalBuy += Integer.parseInt(value);
                }
            }
            int result = totalSupply - totalBuy;

            writer.write("supply," + totalSupply);
            writer.newLine();
            writer.write("buy," + totalBuy);
            writer.newLine();
            writer.write("result," + result);

        } catch (IOException e) {
            throw new RuntimeException("Can`t open file", e);
        }
    }
}
