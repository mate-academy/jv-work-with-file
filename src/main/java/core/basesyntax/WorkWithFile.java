package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supplyTotal = 0;
        int buyTotal = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 2) {
                    continue;
                }
                String type = parts[0];
                int amount;
                try {
                    amount = Integer.parseInt(parts[1]);
                } catch (NumberFormatException e) {
                    continue;
                }

                if ("supply".equals(type)) {
                    supplyTotal += amount;
                } else if ("buy".equals(type)) {
                    buyTotal += amount;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }

        StringBuilder report = new StringBuilder();
        report.append("supply,").append(supplyTotal).append("\n");
        report.append("buy,").append(buyTotal).append("\n");
        report.append("result,").append(supplyTotal - buyTotal).append("\n");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report.toString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
