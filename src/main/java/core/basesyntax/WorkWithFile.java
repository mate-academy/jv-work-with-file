package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        if (fromFileName == null || toFileName == null) {
            throw new IllegalArgumentException("File names cannot be null");
        }

        int buySum = 0;
        int supplySum = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().toLowerCase().split(",");
                if (parts.length != 2) {
                    continue;
                }

                String operation = parts[0].trim();
                int amount;

                try {
                    amount = Integer.parseInt(parts[1].trim());
                } catch (NumberFormatException e) {
                    continue;
                }

                switch (operation) {
                    case "buy" -> buySum += amount;
                    case "supply" -> supplySum += amount;
                    default -> System.out.println("switch(operation) error");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read input file", e);
        }

        int result = supplySum - buySum;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + supplySum);
            writer.newLine();
            writer.write("buy," + buySum);
            writer.newLine();
            writer.write("result," + result);
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Can't write report to output file", e);
        }
    }
}
