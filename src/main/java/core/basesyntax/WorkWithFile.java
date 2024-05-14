package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName)
            throws IllegalArgumentException {
        int supplyTotal = 0;
        int buyTotal = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String type = parts[0].trim();
                    int value = Integer.parseInt(parts[1].trim());
                    if ("supply".equals(type)) {
                        supplyTotal += value;
                    } else if ("buy".equals(type)) {
                        buyTotal += value;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file");
        }
        int result = supplyTotal - buyTotal;
        if (supplyTotal < 0 || buyTotal < 0 || result < 0) {
            throw new IllegalArgumentException("The file contains wrong data");
        }
        StringBuilder content = new StringBuilder();
        content.append("supply,").append(supplyTotal).append(System.lineSeparator());
        content.append("buy,").append(buyTotal).append(System.lineSeparator());
        content.append("result,").append(result);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(content.toString());
        } catch (IOException e) {
            throw new RuntimeException("Cant write data to file");
        }
    }
}
