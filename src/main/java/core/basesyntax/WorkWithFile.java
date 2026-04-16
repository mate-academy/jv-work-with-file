package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supplySum = 0;
        int buySum = 0;
        try {
            List<String> lines = Files.readAllLines(Path.of(fromFileName));
            for (String line : lines) {
                String[] parts = line.split(",");
                int amount = Integer.parseInt(parts[1]);
                if (parts[0].equals("supply")) {
                    supplySum += amount;
                } else if (parts[0].equals("buy")) {
                    buySum += amount;
                }
            }
            int result = supplySum - buySum;
            List<String> resultLines = Arrays.asList(
                    "supply," + supplySum,
                    "buy," + buySum,
                    "result," + result
            );
            Files.write(Path.of(toFileName), resultLines);

        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }

    }
}
