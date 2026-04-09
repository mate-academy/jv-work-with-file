package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        Path pathFrom = Paths.get(fromFileName);
        Path pathTo = Paths.get(toFileName);

        try {
            List<String> lines = fileReader(pathFrom);
            String result = calculateStatistic(lines);
            fileWriter(pathTo, result);
        } catch (IOException e) {
            throw new RuntimeException("Error from file work: ", e);
        }
    }

    private List<String> fileReader(Path from) throws IOException {
        return Files.readAllLines(from);
    }

    private String calculateStatistic(List<String> lines) {
        int totalSupply = 0;
        int totalBuy = 0;
        String sep = System.lineSeparator();

        for (String line : lines) {
            if (line.isEmpty()) {
                continue;
            }

            String[] parts = line.split(",");
            String type = parts[0];
            int amount = Integer.parseInt(parts[1]);

            if (type.equals(SUPPLY)) {
                totalSupply += amount;
            } else {
                totalBuy += amount;
            }
        }

        return SUPPLY + "," + totalSupply + sep
                + BUY + "," + totalBuy + sep
                + "result," + (totalSupply - totalBuy) + sep;

    }

    private void fileWriter(Path to, String text) throws IOException {
        Files.writeString(to, text);
    }
}
