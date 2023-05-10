package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) throws IOException {
        int supplyTotal = 0;
        int buyTotal = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 2) {
                    String operation = fields[0].trim();
                    int amount = Integer.parseInt(fields[1].trim());
                    if (operation.equalsIgnoreCase("supply")) {
                        supplyTotal += amount;
                    } else if (operation.equalsIgnoreCase("buy")) {
                        buyTotal += amount;
                    }
                }
            }
        }

        int result = supplyTotal - buyTotal;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + supplyTotal + "\n");
            writer.write("buy," + buyTotal + "\n");
            writer.write("result," + result + "\n");
        }
    }
}
