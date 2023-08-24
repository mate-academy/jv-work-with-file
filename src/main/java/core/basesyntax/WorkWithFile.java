package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String lineData;
        int supplyAmount = 0;
        int buyAmount = 0;

        try (BufferedReader inputData = new BufferedReader(new FileReader(fromFileName))) {
            while ((lineData = inputData.readLine()) != null) {
                String[] values = lineData.split(",");
                String operationType = values[0];
                int amount = Integer.parseInt(values[1]);
                if ("supply".equals(operationType)) {
                    supplyAmount += amount;
                } else if ("buy".equals(operationType)) {
                    buyAmount += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while reading input file: " + e.getMessage(), e);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + supplyAmount + System.lineSeparator());
            writer.write("buy," + buyAmount + System.lineSeparator());
            writer.write("result," + (supplyAmount - buyAmount) + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Error while writing output file: " + e.getMessage(), e);
        }
    }
}
