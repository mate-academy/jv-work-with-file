package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedReader newReader = new BufferedReader(new FileReader(fromFileName));
                BufferedWriter newWriter = new BufferedWriter(new FileWriter(toFileName))) {

            int supplyTotal = 0;
            int buyTotal = 0;

            String line;
            while ((line = newReader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 2) {
                    String operationType = fields[0].trim();
                    int amount = Integer.parseInt(fields[1].trim());

                    if ("supply".equals(operationType)) {
                        supplyTotal += amount;
                    } else if ("buy".equals(operationType)) {
                        buyTotal += amount;
                    }
                }
            }

            int result = supplyTotal - buyTotal;

            newWriter.write("supply," + supplyTotal);
            newWriter.newLine();
            newWriter.write("buy," + buyTotal);
            newWriter.newLine();
            newWriter.write("result," + result);

            System.out.println("Report generated successfully!");

        } catch (IOException | NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }
}
