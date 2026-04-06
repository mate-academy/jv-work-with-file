package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    private static class Statistic {
        int supplyTotal;
        int buyTotal;

        Statistic(int supplyTotal, int buyTotal) {
            this.supplyTotal = supplyTotal;
            this.buyTotal = buyTotal;
        }
    }

    private List<String> readLines(Path inputFile) {
        try {
            return Files.readAllLines(inputFile);
        } catch (IOException e) {
            throw new RuntimeException("Error working with files", e);
        }
    }

    private void writeReport(Path outputFile, String report) {
        try {
            Files.writeString(outputFile, report);
        } catch (IOException e) {
            throw new RuntimeException("Error working with files", e);
        }
    }

    private String buildReport(int supplyTotal, int buyTotal) {
        int result = supplyTotal - buyTotal;
        String lineSeparator = System.lineSeparator();
        return "supply," + supplyTotal
                + lineSeparator + "buy," + buyTotal
                + lineSeparator + "result," + result;
    }

    private Statistic calculateStatistic(List<String> lines) {
        int supplyTotal = 0;
        int buyTotal = 0;
        for (String line : lines) {
            String[] parts = line.split(",");
            String operation = parts[0];
            int amount = Integer.parseInt(parts[1]);

            if (operation.equals(SUPPLY)) {
                supplyTotal += amount;
            } else if (operation.equals(BUY)) {
                buyTotal += amount;


            }

        }
        return new Statistic(supplyTotal, buyTotal);

    }

    public void getStatistic(String fromFileName, String toFileName) {
        Path inputFile = Path.of(fromFileName);
        Path outputFile = Path.of(toFileName);


        List<String> lines = readLines(inputFile);
        Statistic statistic = calculateStatistic(lines);

        String report = buildReport(statistic.supplyTotal, statistic.buyTotal);
        writeReport(outputFile, report);
    }

}
