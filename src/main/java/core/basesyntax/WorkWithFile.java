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
        writeToFile(createReport(readFromFile(fromFileName)), toFileName);
    }

    private List<String> readFromFile(String fromMyFileName) {
        Path path = Paths.get(fromMyFileName);
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromMyFileName + ".", e);
        }
    }

    private String createReport(List<String> readFromFile) {
        StringBuilder resultBuilder = new StringBuilder();
        int supplySum = 0;
        int buySum = 0;
        int resultSum;

        for (String result : readFromFile) {
            if (result != null) {
                if (result.startsWith(SUPPLY)) {
                    supplySum += Integer.parseInt(result.substring(SUPPLY.length() + 1));
                }
                if (result.startsWith(BUY)) {
                    buySum += Integer.parseInt(result.substring(BUY.length() + 1));
                }
            }
        }
        resultSum = supplySum - buySum;
        return resultBuilder
                .append(SUPPLY).append(SEPARATOR)
                .append(supplySum)
                .append(System.lineSeparator())
                .append(BUY).append(SEPARATOR).append(buySum)
                .append(System.lineSeparator()).append(RESULT).append(SEPARATOR)
                .append(resultSum).toString();
    }

    private void writeToFile(String report, String toFileName) {
        Path path = Paths.get(toFileName);
        try {
            Files.write(path, Collections.singleton(report));
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file" + toFileName + "." + e);
        }
    }
}
