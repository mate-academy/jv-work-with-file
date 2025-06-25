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
                String[] parts = line.split(",");
                String operation = parts[0];
                int amount = Integer.parseInt(parts[1]);

                if (operation.equals("supply")) {
                    totalSupply += amount;
                } else if (operation.equals("buy")) {
                    totalBuy += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file: " + fromFileName, e);
        }

        int result = totalSupply - totalBuy;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            StringBuilder report = new StringBuilder();
            report.append("supply,").append(totalSupply).append(System.lineSeparator());
            report.append("buy,").append(totalBuy).append(System.lineSeparator());
            report.append("result,").append(result).append(System.lineSeparator());

            writer.write(report.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file: " + toFileName, e);
        }
    }
}
