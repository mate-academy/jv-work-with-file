package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        int supplyTotal = 0;
        int buyTotal = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if ("supply".equals(parts[0])) {
                    supplyTotal += Integer.parseInt(parts[1]);
                } else if ("buy".equals(parts[0])) {
                    buyTotal += Integer.parseInt(parts[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }

        int result = supplyTotal - buyTotal;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + supplyTotal + System.lineSeparator());
            writer.write("buy," + buyTotal + System.lineSeparator());
            writer.write("result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }
}
