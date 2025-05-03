package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int totalSupply = 0;
        int totalBuy = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] record = line.split(",");
                String operationType = record[0];
                int amount = Integer.parseInt(record[1]);
                if ("supply".equals(operationType)) {
                    totalSupply += amount;
                } else if ("buy".equals(operationType)) {
                    totalBuy += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Specified exception text: " + System.lineSeparator(), e);
        }

        int result = totalSupply - totalBuy;
        StringBuilder stats = new StringBuilder()
                .append("supply,").append(totalSupply).append(System.lineSeparator())
                .append("buy,").append(totalBuy).append(System.lineSeparator())
                .append("result,").append(result);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName,false))) {
            writer.write(stats.toString());
        } catch (IOException e) {
            throw new RuntimeException("Specified exception text: " + System.lineSeparator(), e);
        }
    }
}
