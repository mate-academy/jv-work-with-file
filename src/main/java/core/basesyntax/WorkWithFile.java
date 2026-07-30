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
                int amount = Integer.parseInt(parts[1].trim());

                if (parts[0].equals("supply")) {
                    supply += amount;
                } else if (parts[0].equals("buy")) {
                    buy += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int result = supply - buy;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + supply);
            writer.newLine();
            writer.write("buy," + buy);
            writer.newLine();
            writer.write("result," + result);
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
