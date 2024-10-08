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
            String value = reader.readLine();
            while (value != null) {
                String[] valueParts = value.split(",");
                if (valueParts[0].trim().equalsIgnoreCase("buy")) {
                    totalBuy += Integer.parseInt(valueParts[1]);
                } else if (valueParts[0].trim().equalsIgnoreCase("supply")) {
                    totalSupply += Integer.parseInt(valueParts[1]);
                }
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }

        int result = totalSupply - totalBuy;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + String.valueOf(totalSupply) + System.lineSeparator());
            writer.write("buy," + String.valueOf(totalBuy) + System.lineSeparator());
            writer.write("result," + String.valueOf(result) + System.lineSeparator());

        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }
}
