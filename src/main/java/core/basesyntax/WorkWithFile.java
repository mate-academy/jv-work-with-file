package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.rmi.RemoteException;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) throws IOException {
        int supply = 0;
        int buy = 0;
        try {
            List<String> lines = Files.readAllLines(Path.of(fromFileName));

            for (String line : lines) {
                if (line == null || line.isEmpty()) {
                    continue;
                }
                String[] parts = line.split(",");

                if (parts.length < 2) {
                    continue;
                }
                String operation = parts[0].trim();
                int amount;
                try {
                    amount = Integer.parseInt(parts[1].trim());
                } catch (NumberFormatException e) {
                    continue;
                }

                if ("supply".equals(operation)) {
                    supply += amount;
                } else if ("buy".equals(operation)) {
                    buy += amount;
                }
            }

            int result = supply - buy;

            String newLine = System.lineSeparator();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
                writer.write("supply," + supply + newLine);
                writer.write("buy," + buy + newLine);
                writer.write("result," + result + newLine);
                writer.write(newLine);
            }
        } catch (IOException e) {
            throw new RemoteException("Error processing files", e);
        }
    }
}

