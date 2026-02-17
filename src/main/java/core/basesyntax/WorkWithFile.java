package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supplyValue = 0;
        int buyValue = 0;
        int resultValue;

        String line;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String type = parts[0];
                int amount = Integer.parseInt(parts[1]);
                if (type.equals("supply")) {
                    supplyValue += amount;
                } else if (type.equals("buy")) {
                    buyValue += amount;
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        resultValue = supplyValue - buyValue;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + supplyValue + System.lineSeparator());
            writer.write("buy," + buyValue + System.lineSeparator());
            writer.write("result," + resultValue);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
