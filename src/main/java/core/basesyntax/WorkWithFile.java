package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
             FileWriter writer = new FileWriter(toFileName)) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts[0].equals("supply")) {
                    supply += Integer.parseInt(parts[1]);
                } else if (parts[0].equals("buy")) {
                    buy += Integer.parseInt(parts[1]);
                }
            }

            writer.write("supply," + supply + System.lineSeparator());
            writer.write("buy," + buy + System.lineSeparator());
            writer.write("result," + (supply - buy));

        } catch (IOException e) {
            throw new RuntimeException("Error while working with files", e);
        }
    }
}

