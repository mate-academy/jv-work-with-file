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

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                String operation = data[0];
                int amount = Integer.parseInt(data[1]);

                if ("supply".equals(operation)) {
                    supplyTotal += amount;
                } else if ("buy".equals(operation)) {
                    buyTotal += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("An error occurred while writing to the file"
                    + fromFileName, e);
        }
        int result = supplyTotal - buyTotal;
        String report = "supply," + supplyTotal + System.lineSeparator()
                + "buy," + buyTotal + System.lineSeparator()
                + "result," + result;

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("An error occurred while writing to the file" + toFileName, e);
        }
    }
}
