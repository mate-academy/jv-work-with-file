package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String [] parts = line.split(",");
                if (parts.length == 2) {
                    String operation = parts[0].trim();
                    int amount = Integer.parseInt(parts[1].trim());
                    if ("supply".equalsIgnoreCase(operation)) {
                        supply += amount;
                    } else if ("buy".equalsIgnoreCase(operation)) {
                        buy += amount;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(fromFileName, e);
        }
        int result = supply - buy;

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(toFileName))) {
            writer.write("supply," + supply);
            writer.newLine();
            writer.write("buy," + buy);
            writer.newLine();
            writer.write("result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + fromFileName, e);
        }
    }
}
