package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int buyVolume = 0;
        int supplyVolume = 0;
        final String buyOperationType = "buy";
        final String supplyOperationType = "supply";

        try (BufferedReader reader = new BufferedReader(new FileReader(new File(fromFileName)));) {
            String value;
            while ((value = reader.readLine()) != null) {
                String amount = value.substring(value.lastIndexOf(",") + 1);
                if (value.contains(buyOperationType)) {
                    buyVolume += Integer.parseInt(amount);
                }
                if (value.contains(supplyOperationType)) {
                    supplyVolume += Integer.parseInt(amount);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(toFileName)))) {
            writer.write(supplyOperationType + "," + supplyVolume + System.lineSeparator());
            writer.write(buyOperationType + "," + buyVolume + System.lineSeparator());
            writer.write("result," + (supplyVolume - buyVolume));
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }
}
