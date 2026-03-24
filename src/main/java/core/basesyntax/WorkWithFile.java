package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;

        try (BufferedReader reader = Files.newBufferedReader(Path.of(fromFileName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    String operation = parts[0];
                    int amount = Integer.parseInt(parts[1]);

                    if (operation.equals("supply")) {
                        supply += amount;
                    } else if (operation.equals("buy")) {
                        buy += amount;
                    }
                }
        } catch (IOException e) {
            throw new RuntimeException("Can't read fromFileName", e);
        }

        int result = supply - buy;

        String report = "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + result;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't created or write to File", e);
        }
        System.out.println(report);
    }
}
