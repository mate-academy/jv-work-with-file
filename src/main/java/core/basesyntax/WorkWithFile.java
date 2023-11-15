package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    private static final String SUPPLIED = "supply";
    private static final String BOUGHT = "buy";
    private int supplyCount = 0;
    private int buyCount = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder result = new StringBuilder();
        countProducts(fromFileName);
        result.append("supply,").append(supplyCount).append(System.lineSeparator())
                .append("buy,").append(buyCount).append(System.lineSeparator())
                .append("result,").append(supplyCount - buyCount);
        createReport(result.toString(), toFileName);
        buyCount = 0;
        supplyCount = 0;
    }

    private void countProducts(String fromFileName) {
        try (BufferedReader reader = Files.newBufferedReader(Path.of(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] lineSeparatedWords = line.split(",");
                if (lineSeparatedWords[0].equals(SUPPLIED)) {
                    supplyCount += Integer.parseInt(lineSeparatedWords[1]);
                } else if (lineSeparatedWords[0].equals(BOUGHT)) {
                    buyCount += Integer.parseInt(lineSeparatedWords[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
    }

    public void createReport(String report, String toFileName) {
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }
}
