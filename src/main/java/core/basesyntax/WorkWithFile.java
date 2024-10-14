package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        int totalSupply = 0;
        int totalBuy = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int amount = Integer.parseInt(parts[1]);

                if (SUPPLY.equals(parts[0])) {
                    totalSupply += amount;
                } else if (BUY.equals(parts[0])) {
                    totalBuy += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading from file: " + fromFileName, e);
        }

        int result = totalSupply - totalBuy;

        // Теперь записываем результаты в файл
        try (FileWriter writer = new FileWriter(toFileName)) {
            // Используем StringBuilder для эффективной конкатенации строк
            StringBuilder sb = new StringBuilder();
            sb.append(SUPPLY).append(",").append(totalSupply).append(System.lineSeparator());
            sb.append(BUY).append(",").append(totalBuy).append(System.lineSeparator());
            sb.append("result").append(",").append(result).append(System.lineSeparator());

            writer.write(sb.toString());
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file: " + toFileName, e);
        }
    }
}
