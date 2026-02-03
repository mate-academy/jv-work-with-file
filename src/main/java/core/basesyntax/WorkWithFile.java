package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File fileReader = new File(fromFileName);
        File fileWriter = new File(toFileName);
        int supplySum = 0;
        int buySum = 0;
        try {
            List<String> lines = Files.readAllLines(fileReader.toPath());
            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts[0].equals("supply")) {
                    supplySum = supplySum + Integer.parseInt(parts[1].trim());
                } else if (parts[0].equals("buy")) {
                    buySum = buySum + Integer.parseInt(parts[1].trim());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        String report =
                "supply,"
                        + supplySum
                        + "\n"
                        +
                        "buy,"
                        + buySum
                        + "\n"
                        +
                        "result,"
                        + (supplySum - buySum);
        try {
            Files.write(fileWriter.toPath(), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }
}
