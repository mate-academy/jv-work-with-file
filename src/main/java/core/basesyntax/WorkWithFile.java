package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int totalBuy = 0;
        int totalSupply = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");//podział na typ i wartość
                String type = parts[0]; // buy-supply
                int value = Integer.parseInt(parts[1]);//wartość

                if ("buy".equalsIgnoreCase(type)) {
                    totalBuy += value;
                } else if ("supply".equalsIgnoreCase(type)) {
                    totalSupply += value;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + e.getMessage(), e);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid number format: " + e.getMessage(), e);
        }

        String result = "supply," + totalSupply + System.lineSeparator()
                + "buy," + totalBuy + System.lineSeparator()
                + "result," + (totalSupply - totalBuy);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Error writing file:" + e.getMessage(), e);
        }
    }
}

