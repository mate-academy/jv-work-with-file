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

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String label = parts[0];
                    int value = Integer.parseInt(parts[1]);
                    if ("supply".equals(label)) {
                        supplyTotal += value;
                    } else if ("buy".equals(label)) {
                        buyTotal += value;
                    }
                }
            }

            int result = supplyTotal - buyTotal;

            BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName));
            writer.write("supply," + supplyTotal);
            writer.newLine();
            writer.write("buy," + buyTotal);
            writer.newLine();
            writer.write("result," + result);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
    }
}
