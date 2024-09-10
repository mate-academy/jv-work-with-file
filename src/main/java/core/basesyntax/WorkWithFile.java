package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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
                String[] split = line.split(",");
                String operation = split[0];
                int amount = Integer.parseInt(split[1]);

                if (operation.equals("supply")) {
                    supplySum += amount;
                }
                if (operation.equals("buy")) {
                    buySum += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while reading file " + fromFileName, e);
        }

        int result = supplySum - buySum;

        String report = "supply," + supplySum + System.lineSeparator()
                + "buy," + buySum + System.lineSeparator()
                + "result," + result;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Error while writing file " + toFileName, e);
        }
    }
}

