package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supplySum = 0;
        int buySum = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String operation = parts[0].trim();
                int amount = Integer.parseInt(parts[1].trim());

                if ("supply".equalsIgnoreCase(operation)) {
                    supplySum += amount;
                } else if ("buy".equalsIgnoreCase(operation)) {
                    buySum += amount;
                }
            }

            try (FileWriter writer = new FileWriter(toFileName)) {
                writer.write("supply," + supplySum + "\n");
                writer.write("buy," + buySum + "\n");
                writer.write("result," + (supplySum - buySum) + "\n");
                System.out.println("Raport zapisany do pliku: " + toFileName);
            }

        } catch (IOException e) {
            throw new RuntimeException("Błąd podczas odczytu pliku: " + fromFileName, e);
        }
    }
}
