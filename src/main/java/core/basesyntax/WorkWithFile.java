package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class WorkWithFile {
    private static final String comma = ",";
    private static final String supply = "supply";
    private static final String buy = "buy";
    private static final String result = "result";

    public void getStatistic(String fromFile, String toFile) {
        int supplyTotal = 0;
        int buyTotal = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 2) {
                    continue;
                }
                String operation = parts[0].trim();
                String amountStr = parts[1].trim();
                int amount;
                try {
                    amount = Integer.parseInt(amountStr);
                } catch (NumberFormatException e) {
                    throw new RuntimeException("Invalid number format in file " + fromFile + ": " + line, e);
                }
                if (operation.equals("supply")) {
                    supplyTotal += amount;
                } else if (operation.equals("buy")) {
                    buyTotal += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading file " + fromFile, e);
        }
        int resultTotal = supplyTotal - buyTotal;
         StringBuilder reportBuilder = new StringBuilder();
         reportBuilder.append(supply).append(comma).append(supplyTotal).append(System.lineSeparator());
         reportBuilder.append(buy).append(comma).append(buyTotal).append(System.lineSeparator());
         reportBuilder.append(result).append(comma).append(resultTotal);
         try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFile))) {
              writer.write(reportBuilder.toString());
         } catch (IOException e) {
             throw new RuntimeException("Error write data to file " + toFile, e);
         }
    }
}
