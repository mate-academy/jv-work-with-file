package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        int totalSupply = 0;
        int totalBuy = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                String type = values[0];
                int amount = Integer.parseInt(values[1]);
                if (type.equals("supply")) {
                    totalSupply += amount;
                } else if (type.equals("buy")) {
                    totalBuy += amount;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Can't read from this file", e);
        }
        int result = totalSupply - totalBuy;
        stringBuilder.append("supply,").append(totalSupply).append(System.lineSeparator());
        stringBuilder.append("buy,").append(totalBuy).append(System.lineSeparator());
        stringBuilder.append("result,").append(result);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't run this file", e);
        }
    }
}
