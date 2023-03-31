package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            FileWriter writer = new FileWriter(toFileName);

            String line = reader.readLine();
            int supplyTotal = 0;
            int buyTotal = 0;

            while (line != null) {
                String[] values = line.split(",");
                if (values[0].equals("supply")) {
                    supplyTotal += Integer.parseInt(values[1]);
                } else if (values[0].equals("buy")) {
                    buyTotal += Integer.parseInt(values[1]);
                }
                line = reader.readLine();
            }

            int result = supplyTotal - buyTotal;

            writer.write("supply," + supplyTotal + "\n");
            writer.write("buy," + buyTotal + "\n");
            writer.write("result," + result + "\n");

            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
