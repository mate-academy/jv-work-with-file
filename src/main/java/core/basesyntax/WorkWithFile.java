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
                if (line.trim().isEmpty()) {
                    continue;
                }
                if ("supply".equals(parts[0].trim())) {
                    supply += Integer.parseInt(parts[1].trim());
                } else if ("buy".equals(parts[0].trim())) {
                    buy += Integer.parseInt(parts[1].trim());
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Can't read file ", e);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + supply + System.lineSeparator());
            writer.write("buy," + buy + System.lineSeparator());
            writer.write("result," + (supply - buy));
        } catch (Exception e) {
            throw new RuntimeException("Can't write data to file " + toFileName);
        }
    }
}
