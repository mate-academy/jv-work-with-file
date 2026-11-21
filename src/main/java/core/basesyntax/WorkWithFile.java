package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                String operation = parts[0];
                int amount = Integer.parseInt(parts[1]);

                if ("supply".equals(operation)) {
                    supply += amount;
                } else if ("buy".equals(operation)) {
                    buy += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file: " + fromFileName, e);
        }

        int result = supply - buy;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + supply);
            writer.newLine();

            writer.write("buy," + buy);
            writer.newLine();

            writer.write("result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: " + toFileName, e);
        }
    }
}
