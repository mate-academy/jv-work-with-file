package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int INDEX_OF_SUPPLY_LENGTH = 7;
    private static final int INDEX_OF_BUY_LENGTH = 4;
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) {
        File fileForRead = new File(fromFileName);
        StringBuilder firstBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileForRead))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                firstBuilder.append(value).append(" ");
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read this file", e);
        }
        int countSupply = 0;
        int countBuy = 0;
        String [] amounts = firstBuilder.toString().split(" ");
        for (String amount : amounts) {
            if (amount.contains("supply")) {
                countSupply += Integer.parseInt(amount.substring(INDEX_OF_SUPPLY_LENGTH,
                        amount.length()));
            } else {
                countBuy += Integer.parseInt(amount.substring(INDEX_OF_BUY_LENGTH,
                        amount.length()));
            }
        }
        String forWright = "supply," + countSupply + LINE_SEPARATOR
                + "buy," + countBuy + LINE_SEPARATOR
                + "result," + (countSupply - countBuy) + LINE_SEPARATOR;
        File fileForWright = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileForWright))) {
            bufferedWriter.write(forWright);
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException("We can't write this file", e);
        }
    }
}
