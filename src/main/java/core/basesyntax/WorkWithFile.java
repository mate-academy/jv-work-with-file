package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File fileRead = new File(fromFileName);
        File fileWrite = new File(toFileName);

        try (BufferedReader reader = new BufferedReader(new FileReader(fileRead));
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileWrite))) {

            int supplySum = 0;
            int buySum = 0;
            String value = reader.readLine();

            while (value != null) {
                String[] parts = value.split(",");
                String operation = parts[0];
                int amount = Integer.parseInt(parts[1]);

                if (operation.equals("supply")) {
                    supplySum += amount;
                } else if (operation.equals("buy")) {
                    buySum += amount;
                }
                value = reader.readLine();
            }

            int result = supplySum - buySum;

            writer.write("supply," + supplySum + System.lineSeparator());
            writer.write("buy," + buySum + System.lineSeparator());
            writer.write("result," + result + System.lineSeparator());

        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }
}
