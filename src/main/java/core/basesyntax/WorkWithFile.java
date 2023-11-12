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
            String value;
            while ((value = reader.readLine()) != null) {
                String[] element = value.split(",");
                if (element[0].equals("supply")) {
                    supplySum = supplySum + Integer.parseInt(element[1]);
                } else if (element[0].equals("buy")) {
                    buySum = buySum + Integer.parseInt(element[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + supplySum);
            writer.newLine();
            writer.write("buy," + buySum);
            writer.newLine();
            writer.write("result," + (supplySum - buySum));
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }
}
