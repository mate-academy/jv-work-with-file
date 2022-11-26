package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String WORD_SUPPLY = "supply";
    private static final String WORD_BUY = "buy";
    private static final String WORD_RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(" ");
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        int sumSupply = 0;
        int sumBuy = 0;
        String[] strings = stringBuilder.toString().split(" ");
        for (int i = 0; i < strings.length; i++) {
            String[] s = strings[i].split(",");
            if (s[0].equals(WORD_SUPPLY)) {
                sumSupply += Integer.parseInt(s[1]);
            } else {
                sumBuy += Integer.parseInt(s[1]);
            }
        }
        int difference = sumSupply - sumBuy;
        StringBuilder builder = new StringBuilder();
        String writeData = builder.append(WORD_SUPPLY).append(",").append(sumSupply)
                .append(System.lineSeparator())
                .append(WORD_BUY).append(",").append(sumBuy)
                .append(System.lineSeparator())
                .append(WORD_RESULT).append(",").append(difference).toString();
        File file1 = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file1))) {
            bufferedWriter.write(writeData);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
