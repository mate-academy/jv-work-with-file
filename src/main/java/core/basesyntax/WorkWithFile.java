package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {

        int supply = 0;
        int buy = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] parts = line.split(",");
                String type = parts[0].trim();
                int amount = Integer.parseInt(parts[1].trim());

                if (type.equals("supply")) {
                    supply += amount;
                }
                if (type.equals("buy")) {
                    buy += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }

        try (FileWriter writer = new FileWriter(toFileName)) {
            int result = supply - buy;
            writer.write("supply," + supply + System.lineSeparator());
            writer.write("buy," + buy + System.lineSeparator());
            writer.write("result," + result + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Can't write from file", e);
        }
    }
}
