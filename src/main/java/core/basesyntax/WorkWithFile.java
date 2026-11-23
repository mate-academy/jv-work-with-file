package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;

        try {
            List<String> lines = Files.readAllLines(Path.of(fromFileName));
            for (String line : lines) {
                String[] parts = line.split(",");
                String operation = parts[0];
                int amount = Integer.parseInt(parts[1]);

                if (operation.equals("supply")) {
                    supply += amount;
                } else if (operation.equals("buy")) {
                    buy += amount;
                }
            }
            int result = supply - buy;
            String separator = System.lineSeparator();
            String report = "supply," + supply + separator
                    + "buy," + buy + separator
                    + "result," + result;
            Files.writeString(Path.of(toFileName), report);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
