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

        try (BufferedReader br = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 2) {
                    continue;
                }
                String operationType = parts[0].trim();
                int amount;
                try {
                    amount = Integer.parseInt(parts[1].trim());
                } catch (NumberFormatException e) {
                    continue;
                }
                if ("supply".equals(operationType)) {
                    totalSupply += amount;
                } else if ("buy".equals(operationType)) {
                    totalBuy += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        StringBuilder report = new StringBuilder();
        int result = totalSupply - totalBuy;
        report.append("supply,").append(totalSupply).append(System.lineSeparator());
        report.append("buy,").append(totalBuy).append(System.lineSeparator());
        report.append("result,").append(result).append(System.lineSeparator());

        try (BufferedWriter wr = new BufferedWriter(new FileWriter(toFileName))) {
            wr.write(report.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }
}
