package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {

        StringBuilder sb = new StringBuilder();
        int supplyTotal = 0;
        int buyTotal = 0;

        try {
            List<String> lines = Files.readAllLines(Path.of(fromFileName));
            for (String line: lines) {
                String[] split = line.split(",");
                if (split[0].equals("supply")) {
                    supplyTotal += Integer.parseInt(split[1]);
                } else {
                    buyTotal += Integer.parseInt(split[1]);
                }
            }

            sb.append("supply,")
                    .append(supplyTotal)
                    .append(System.lineSeparator())
                    .append("buy,")
                    .append(buyTotal)
                    .append(System.lineSeparator())
                    .append("result,")
                    .append(supplyTotal - buyTotal);

        } catch (IOException e) {
            throw new RuntimeException("Can't read from file!", e);
        }

        try {
            Files.writeString(Path.of(toFileName), sb.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write in file!", e);
        }
    }
}
