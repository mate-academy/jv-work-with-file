package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
                BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            String values = reader.readLine();
            int totalSupply = 0;
            int totalBuy = 0;

            while (values != null) {
                String[] value = values.split(",");

                if (value[0].equals("supply")) {
                    totalSupply += Integer.parseInt(value[1]);
                } else {
                    totalBuy += Integer.parseInt(value[1]);
                }

                values = reader.readLine();
            }

            int result = totalSupply - totalBuy;

            writer.write("supply," + totalSupply + System.lineSeparator()
                    + "buy," + totalBuy + System.lineSeparator()
                    + "result," + result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
