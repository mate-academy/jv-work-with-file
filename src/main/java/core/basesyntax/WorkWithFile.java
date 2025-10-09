package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String content = reader.readLine();
            int totalSupply = 0;
            int totalBuy = 0;
            int result = 0;
            while (content != null) {
                String[] parts = content.split(",",2);
                String operation = parts[0].trim();

                if (parts.length != 2) {
                    continue;
                }

                int amount = Integer.parseInt(parts[1].trim());
                content = reader.readLine();

                switch (operation) {
                    case "supply":
                        totalSupply += amount;
                        break;
                    case "buy":
                        totalBuy += amount;
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid operation: " + operation);
                }
            }
            result = totalSupply - totalBuy;
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
                writer.write("supply," + totalSupply + System.lineSeparator());
                writer.write("buy," + totalBuy + System.lineSeparator());
                writer.write("result," + result + System.lineSeparator());
            } catch (IOException e) {
                throw new RuntimeException("Can't create file");
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file");
        }
    }
}
