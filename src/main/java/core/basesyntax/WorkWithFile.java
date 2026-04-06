package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        Path inputFile = Path.of(fromFileName);
        Path outputFile = Path.of(toFileName);
        int supplyTotal = 0;
        int buyTotal = 0;

        try {
            List<String> lines = Files.readAllLines(inputFile);
            for (String line : lines) {
                String[] parts = line.split(",");
                String operation = parts[0];
                int amount = Integer.parseInt(parts[1]);

                if (operation.equals("supply")) {
                    supplyTotal += amount;
                } else if (operation.equals("buy")) {
                    buyTotal += amount;
                }


            }
            int result = supplyTotal - buyTotal;
            String lineSeparator = System.lineSeparator();
            String report = "supply," + supplyTotal
                    + lineSeparator + "buy,"
                    + buyTotal + lineSeparator + "result," + result;
            Files.writeString(outputFile, report);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
