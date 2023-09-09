package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        int result = 0;

        try {
            String[] lines = Files.readString(Path.of(fromFileName)).split(System.lineSeparator());

            for (String line : lines) {
                String[] data = line.split(",");
                String operation = data[0].trim();
                int amount = Integer.parseInt(data[1].trim());

                if (operation.equals("supply")) {
                    supply += amount;
                } else if (operation.equals("buy")) {
                    buy += amount;
                }
            }

            result = supply - buy;

            String supplyResult = "supply," + supply + System.lineSeparator();
            String buyResult = "buy," + buy + System.lineSeparator();
            String resultResult = "result," + result + System.lineSeparator();
            String report = supplyResult + buyResult + resultResult;

            Files.writeString(Path.of(toFileName), report);
        } catch (IOException e) {
            throw new RuntimeException("Can't correctly read data from file " + fromFileName, e);
        }
    }
}
