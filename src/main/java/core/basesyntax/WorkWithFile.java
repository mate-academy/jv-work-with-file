package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        try {
            String content = Files.readString(Path.of(fromFileName));
            String[] lines = content.split("\\R");
            int supply = 0;
            int buy = 0;
            for (String line : lines) {
                String[] parts = line.split(",");
                String operation = parts[0];
                int amount = Integer.parseInt(parts[1]);
                if (operation.equals("supply")) {
                    supply += amount;
                } else {
                    buy += amount;
                }
            }
            try {
                String data = "supply," + supply + System.lineSeparator()
                        + "buy," + buy + System.lineSeparator()
                        + "result," + (supply - buy);
                Files.writeString(Path.of(toFileName), data);
            } catch (IOException e) {
                throw new RuntimeException("Can't write to file!", e);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read a file!", e);
        }

    }
}
