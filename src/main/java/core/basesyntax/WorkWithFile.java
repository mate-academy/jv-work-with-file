package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WorkWithFile {

    private static final Logger LOGGER = Logger.getLogger(WorkWithFile.class.getName());

    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
                BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {

            int supplyTotal = 0;
            int buyTotal = 0;
            String line;

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 2) {
                    String operationType = values[0].trim();
                    int amount = Integer.parseInt(values[1].trim());

                    if ("supply".equals(operationType)) {
                        supplyTotal += amount;
                    } else if ("buy".equals(operationType)) {
                        buyTotal += amount;
                    }
                }
            }

            int result = supplyTotal - buyTotal;

            writer.write("supply," + supplyTotal);
            writer.newLine();
            writer.write("buy," + buyTotal);
            writer.newLine();
            writer.write("result," + result);

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Exeption pri obrabotke: " + e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        WorkWithFile fileProcessor = new WorkWithFile();
        fileProcessor.getStatistic("orange.csv", "grape.csv");
        System.out.println("All is ok!");
    }
}
