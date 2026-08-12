package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supplySum = 0;
        int buySum = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {

            String line;

            while ((line = reader.readLine()) != null) {
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
            String report = "supply," + supplySum + "\n"
                    + "buy," + buySum + "\n"
                    + "result," + result;
            Files.writeString(Path.of(toFileName), report);

        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: ", e);
        }
    }
}
