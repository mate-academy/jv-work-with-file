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

            int supplyTotal = 0;
            int buyTotal = 0;

            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String operationType = data[0].trim();
                int amount = Integer.parseInt(data[1].trim());

                switch (operationType) {
                    case "supply":
                        supplyTotal += amount;
                        break;
                    case "buy":
                        buyTotal += amount;
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid operation type: "
                                + operationType);
                }
            }

            int result = supplyTotal - buyTotal;

            writer.write("supply," + supplyTotal);
            writer.newLine();
            writer.write("buy," + buyTotal);
            writer.newLine();
            writer.write("result," + result);

        } catch (IOException e) {
            throw new RuntimeException("Error processing the file", e);
        }
    }
}
