package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int amountBuy = 0;
        int amountSupply = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String operationType = parts[0];
                int amount = Integer.parseInt(parts[1]);

                if (operationType.equals("buy")) {
                    amountBuy += amount;
                } else if (operationType.equals("supply")) {
                    amountSupply += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }

        int result = amountSupply - amountBuy;

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write("supply," + amountSupply + System.lineSeparator());
            bufferedWriter.write("buy," + amountBuy + System.lineSeparator());
            bufferedWriter.write("result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file", e);
        }
    }
}
