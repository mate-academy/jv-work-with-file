package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        Path pathFrom = Paths.get(fromFileName);
        Path pathTo = Paths.get(toFileName);

        int totalSupply = 0;
        int totalBuy = 0;
        String sep = System.lineSeparator();

        try {
            List<String> lines = Files.readAllLines(pathFrom);

            for (String line : lines) {
                if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split(",");
                String type = parts[0];
                int amount = Integer.parseInt(parts[1]);

                if (type.equals("supply")) {
                    totalSupply += amount;
                } else {
                    totalBuy += amount;
                }
            }

            String result = "supply," + totalSupply + sep
                    + "buy," + totalBuy + sep
                    + "result," + (totalSupply - totalBuy) + sep;

            Files.writeString(pathTo, result);
        } catch (IOException e) {
            System.out.println("Error from file work: " + e);
        }
    }
}
