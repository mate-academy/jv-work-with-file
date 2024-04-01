package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String line = "";
        int supply = 0;
        int buy = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fromFileName))) {
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 2) {
                    throw new IllegalArgumentException("Invalid line format: " + line);
                }
                String temp = parts[0];
                int amount = Integer.parseInt(parts[1].trim());
                if ("supply".equals(temp)) {
                    supply += amount;
                } else if ("buy".equals(temp)) {
                    buy += amount;
                } else {
                    throw new IllegalArgumentException("Unknown type: " + temp);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + fromFileName, e);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + supply + System.lineSeparator());
            writer.write("buy," + buy + System.lineSeparator());
            writer.write("result," + (supply - buy));
        } catch (IOException e) {
            throw new RuntimeException("Error writing file: " + toFileName, e);
        }
    }
}
