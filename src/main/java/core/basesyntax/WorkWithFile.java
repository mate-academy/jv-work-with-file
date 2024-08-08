package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {

        int supplyTotal = 0;
        int buyTotal = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",");
                String operationType = parts[0];
                int amount = Integer.parseInt(parts[1]);

                if (operationType.equals("supply")) {
                    supplyTotal += amount;
                } else if (operationType.equals("buy")) {
                    buyTotal += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("An error occurred while reading the file");
        }
        int result = supplyTotal - buyTotal;

        List<String> lines = new ArrayList<>();
        lines.add("supply," + supplyTotal);
        lines.add("buy," + buyTotal);
        lines.add("result," + result);

        try {
            Files.write(Path.of(toFileName), lines);
        } catch (IOException e) {
            throw new RuntimeException("An error occurred while reading the file");
        }
    }
}

