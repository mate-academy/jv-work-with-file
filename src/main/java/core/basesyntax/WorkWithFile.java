package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class WorkWithFile {
    private static final int ACTION = 0;
    private static final int AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String action = parts[ACTION];
                String amount = parts[AMOUNT];
                if ("buy".equals(action)) {
                    buy += Integer.parseInt(amount);
                } else if ("supply".equals(action)) {
                    supply += Integer.parseInt(amount);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }

        File toFile = new File(toFileName);
        try {
            toFile.createNewFile();
            Files.writeString(
                    toFile.toPath(),
                    "supply," + supply + System.lineSeparator(),
                    StandardOpenOption.APPEND
            );
            Files.writeString(
                    toFile.toPath(),
                    "buy," + buy + System.lineSeparator(),
                    StandardOpenOption.APPEND
            );
            Files.writeString(
                    toFile.toPath(),
                    "result," + (supply - buy),
                    StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            throw new RuntimeException("Failed to write to file", e);
        }
    }
}
