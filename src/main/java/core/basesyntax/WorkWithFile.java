package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class WorkWithFile {
    private static final String SEPARATOR = "\\s*,\\s*";
    private static final int TYPE = 0;
    private static final int SIZE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int totalSupply = 0;
        int totalBuy = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
             BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(SEPARATOR);
                String operationType = parts[TYPE];
                int quantity = Integer.parseInt(parts[SIZE]);

                if ("supply".equals(operationType)) {
                    totalSupply += quantity;
                } else if ("buy".equals(operationType)) {
                    totalBuy += quantity;
                }
            }

            writer.write("supply," + totalSupply + "\nbuy," + totalBuy + "\nresult,"
                    + (totalSupply - totalBuy));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}