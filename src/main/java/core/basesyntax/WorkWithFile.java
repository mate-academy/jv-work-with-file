package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String line;
        int totalSupply = 0;
        int totalBuy = 0;
        try (BufferedReader readFromFile = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = readFromFile.readLine()) != null) {
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
            throw new RuntimeException("Can`t read file", e);
        }
        String[] report = new String[] {
                "supply," + totalSupply,
                "buy," + totalBuy,
                "result," + (totalSupply - totalBuy)
        };
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            for (String r : report) {
                writer.write(r);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
