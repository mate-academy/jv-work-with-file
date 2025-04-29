package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        int supplySum = 0;
        int buySum = 0;

        try {
            List<String> lines = Files.readAllLines(Path.of(fromFileName));

            for (String line : lines) {
                String[] parts = line.split(",");
                String operation = parts[0];
                int amount = Integer.parseInt(parts[1]);

                if (operation.equals("supply")) {
                    supplySum += amount;
                } else if (operation.equals("buy")) {
                    buySum += amount;
                }
            }

            int result = supplySum - buySum;

            String output = "supply," + supplySum + System.lineSeparator()
                + "buy," + buySum + System.lineSeparator()
                + "result," + result;

            Files.write(Path.of(toFileName), output.getBytes());

        } catch (IOException e) {
            throw new RuntimeException("Error while working with file", e);
        }
    }
}
