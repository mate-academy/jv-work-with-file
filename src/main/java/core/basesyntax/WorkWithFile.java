package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        List<String> statistic;
        try {
            statistic = Files.readAllLines(new File(fromFileName).toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }

        int supplySum = 0;
        int buySum = 0;

        for (String line : statistic) {
            String[] parts = line.split(",");
            String operation = parts[0];
            int amount = Integer.parseInt(parts[1]);

            switch (operation) {
                case "supply":
                    supplySum += amount;
                    break;
                case "buy":
                    buySum += amount;
                    break;
                default:
                    throw new RuntimeException("Unknown operation: " + operation);
            }
        }

        int result = supplySum - buySum;

        List<String> resultLines = new ArrayList<>();
        resultLines.add("supply," + supplySum);
        resultLines.add("buy," + buySum);
        resultLines.add("result," + result);

        File fileToWrite = new File(toFileName);
        try {
            Files.write(
                    fileToWrite.toPath(),
                    resultLines, StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }
}
