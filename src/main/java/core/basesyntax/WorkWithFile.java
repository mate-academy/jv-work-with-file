package core.basesyntax;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;

        try (BufferedReader br = Files.newBufferedReader(Paths.get(fromFileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] parts = line.split(",");
                if (parts.length < 2) {
                    continue;
                }
                String from = parts[0].trim();
                int amount;
                try {
                    amount = Integer.parseInt(parts[1].trim());
                } catch (NumberFormatException ex) {
                    throw new RuntimeException("Invalid number: " + line, ex);
                }

                if (SUPPLY.equals(from)) {
                    supply += amount;
                } else if (BUY.equals(from)) {
                    buy += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file " + fromFileName, e);

        }
        List<String> report = createReport(supply, buy);
        writeToFile(report, toFileName);
    }

    private List<String> createReport(int supply, int buy) {
        return Arrays.asList(
                SUPPLY + "," + supply,
                BUY + "," + buy,
                RESULT + "," + (supply - buy)
        );
    }

    private void writeToFile(List<String> report, String toFileName) {
        try {
            Files.write(Paths.get(toFileName), report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file " + toFileName, e);
        }
    }
}
