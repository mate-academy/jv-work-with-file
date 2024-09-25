package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String DELIMITER = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int amountBuy = 0;
        int amountSupply = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(DELIMITER);
                String operationType = parts[0].trim();
                int amount = Integer.parseInt(parts[1].trim());

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
            StringBuilder stringBuilder = new StringBuilder();
            bufferedWriter.write(stringBuilder.append("supply,").append(amountSupply)
                    .append(System.lineSeparator()).toString());
            stringBuilder.setLength(0);
            bufferedWriter.write(stringBuilder.append("buy,").append(amountBuy)
                    .append(System.lineSeparator()).toString());
            stringBuilder.setLength(0);
            bufferedWriter.write(stringBuilder.append("result,").append(result).toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file", e);
        }
    }
}
