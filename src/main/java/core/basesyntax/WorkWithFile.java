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

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        try {
            List<String> text = Files.readAllLines(Path.of(file.toURI()));
            StringBuilder builder = new StringBuilder();
            int supplySum = 0;
            int buySum = 0;
            for (String line : text) {
                if (line.startsWith(SUPPLY)) {
                    supplySum += Integer.parseInt(line.split(",")[INDEX_OF_COUNT]);
                } else {
                    buySum += Integer.parseInt(line.split(",")[INDEX_OF_COUNT]);
                }
            }
            builder.append(SUPPLY).append(",").append(supplySum).append(System.lineSeparator());
            builder.append(BUY).append(",").append(buySum).append(System.lineSeparator());
            builder.append(RESULT).append(",").append(supplySum - buySum);
            writeToFile(toFileName, builder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can not read file", e);
        }
    }

    private void writeToFile(String fileName, String report) {
        File file = new File(fileName);
        try {
            Files.write(Path.of(file.toURI()), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can not write to file", e);
        }
    }

}
