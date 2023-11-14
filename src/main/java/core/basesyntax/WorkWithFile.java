package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    private static final Integer INDEX_OF_COUNT = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> text = readFromFile(fromFileName);
        String statistic = calculateStatistic(text);
        writeToFile(toFileName, statistic);
    }

    private List<String> readFromFile(String fileName) {
        File file = new File(fileName);
        List<String> text;
        try {
            text = Files.readAllLines(Path.of(file.toURI()));
        } catch (IOException e) {
            throw new RuntimeException("Can not read file " + fileName, e);
        }
        return text;
    }

    private String calculateStatistic(List<String> text) {
        StringBuilder builder = new StringBuilder();
        int supplySum = 0;
        int buySum = 0;
        for (String line : text) {
            if (line.startsWith(SUPPLY)) {
                supplySum += Integer.parseInt(line.split(COMA)[INDEX_OF_COUNT]);
            } else {
                buySum += Integer.parseInt(line.split(COMA)[INDEX_OF_COUNT]);
            }
        }
        builder.append(SUPPLY).append(COMA).append(supplySum).append(System.lineSeparator());
        builder.append(BUY).append(COMA).append(buySum).append(System.lineSeparator());
        builder.append(RESULT).append(COMA).append(supplySum - buySum);
        return builder.toString();
    }

    private void writeToFile(String fileName, String report) {
        File file = new File(fileName);
        try {
            Files.write(Path.of(file.toURI()), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can not write to file " + fileName, e);
        }
    }
}
