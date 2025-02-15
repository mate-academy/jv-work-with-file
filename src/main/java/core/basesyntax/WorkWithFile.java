package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supplyTotal = 0;
        int buyTotal = 0;

        try {
            List<String> lines = Files.readAllLines(Path.of(fromFileName));
            for (String line : lines) {
                String[] parts = line.split(",");
                int sum = Integer.parseInt(parts[1]);
                if(parts[0].equals("supply")) {
                    supplyTotal += sum;
                } else  if (parts[0].equals("buy")) {
                    buyTotal += sum;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int result = supplyTotal - buyTotal;

        String report = "supply," + supplyTotal + System.lineSeparator()
                + "buy," + buyTotal + System.lineSeparator()
                + "result," + result;

        try {
            Files.write(Path.of(toFileName), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}