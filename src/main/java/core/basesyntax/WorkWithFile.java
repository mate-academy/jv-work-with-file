package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        String line;
        String splitBy = ",";
        int sumSupply = 0;
        int sumBuy = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            while ((line = reader.readLine()) != null) {
                String[] trade = line.split(splitBy);
                for (int i = 0; i < trade.length; i++) {
                    if (trade[i].equals(SUPPLY)) {
                        sumSupply += Integer.parseInt(trade[i + 1]);
                    } else if (trade[i].equals(BUY)) {
                        sumBuy += Integer.parseInt(trade[i + 1]);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        int sumResult = sumSupply - sumBuy;
        StringBuilder writebuilder = new StringBuilder();
        String strtoFile = writebuilder.append(SUPPLY).append(splitBy)
                .append(sumSupply).append(System.lineSeparator())
                .append(BUY).append(splitBy).append(sumBuy).append(System.lineSeparator())
                .append("result").append(splitBy).append(sumResult).toString();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(strtoFile);
        } catch (IOException ex) {
            throw new RuntimeException("Can't write file", ex);
        }
    }
}
