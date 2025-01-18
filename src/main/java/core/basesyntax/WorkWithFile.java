package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String supply = "supply";
        String buy = "buy";
        int counterSupply = 0;
        int counterBuy = 0;
        Pattern pattern = Pattern.compile("[^A-Za-z0-9]+",Pattern.CASE_INSENSITIVE);
        Matcher matcher;
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] lineSplit = line.split(",");
                matcher = pattern.matcher(lineSplit[0]);
                String action = matcher.replaceAll("");
                if (supply.equals(action)) {
                    counterSupply += Integer.parseInt(lineSplit[1]);
                } else if (buy.equals(action)) {
                    counterBuy += Integer.parseInt(lineSplit[1]);
                }
            }
            int result = counterSupply - counterBuy;

            stringBuilder.append("supply,").append(counterSupply).append(System.lineSeparator())
                    .append("buy,").append(counterBuy).append(System.lineSeparator())
                    .append("result,").append(result);
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(String.valueOf(stringBuilder));
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
