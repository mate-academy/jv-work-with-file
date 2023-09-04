package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> data = readFromFile(fromFileName);
        String report = createReport(data);
        writeToFile(report, toFileName);
    }

    private List<String> readFromFile(String fromFileName) {
        try {
            Path path = Paths.get(fromFileName);
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
    }

    private String createReport(List<String> data) {
        int supplySum = 0;
        int buySum = 0;

        for (String line : data) {
            if (line != null) {
                if (line.startsWith(SUPPLY)) {
                    supplySum += Integer.parseInt(line.substring(SUPPLY.length() + 1));
                }
                if (line.startsWith(BUY)) {
                    buySum += Integer.parseInt(line.substring(BUY.length() + 1));
                }
            }
        }

        int resultSum = supplySum - buySum;

        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append(SUPPLY).append(SEPARATOR).append(supplySum)
                .append(System.lineSeparator())
                .append(BUY).append(SEPARATOR).append(buySum)
                .append(System.lineSeparator())
                .append(RESULT).append(SEPARATOR).append(resultSum);

        return reportBuilder.toString();
    }

    private void writeToFile(String report, String toFileName) {
        try {
            Path path = Paths.get(toFileName);
            Files.write(path, Collections.singleton(report));
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }
}
