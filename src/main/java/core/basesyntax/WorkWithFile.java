package core.basesyntax;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supplySum = 0;
        int buySum = 0;
        try {
            List<String> lines = Files.readAllLines(Paths.get(fromFileName));
            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts[0].equals("supply")) {
                    supplySum += Integer.parseInt(parts[1]);
                } else if (parts[0].equals("buy")) {
                    buySum += Integer.parseInt(parts[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }

        int result = supplySum - buySum;
        String report = "supply," + supplySum + System.lineSeparator()
                + "buy," + buySum + System.lineSeparator()
                + "result," + result;

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }
}

