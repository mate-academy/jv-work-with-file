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
        int result = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String content = reader.readLine();
            while (content != null) {
                if (content.isBlank()) {
                    content = reader.readLine();
                    continue;
                }

                String[] parts = content.split(",", 2);
                if (parts.length != 2) {
                    throw new IllegalArgumentException("Bad CSV row: " + content);
                }
                String operation = parts[0].trim();
                String amountStr = parts[1].trim();

                int amount;
                try {
                    amount = Integer.parseInt(amountStr);
                } catch (NumberFormatException ex) {
                    throw new RuntimeException("Bad amount '" + amountStr
                            + "' in row: " + content, ex);
                }

                switch (operation.toLowerCase()) {
                    case "supply":
                        totalSupply += amount;
                        break;
                    case "buy":
                        totalBuy += amount;
                        break;
                    default:
                        throw new RuntimeException("Invalid operation: " + operation);
                }
                content = reader.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fromFileName, e);
        }
        result = totalSupply - totalBuy;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + totalSupply + System.lineSeparator());
            writer.write("buy," + totalBuy + System.lineSeparator());
            writer.write("result," + result + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Can't create file" + toFileName, e);
        }
    }
}
