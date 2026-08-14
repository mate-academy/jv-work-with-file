package core.basesyntax;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";

    private static final String BUY_RESULT = "buy,";
    private static final String SUPPLIES_RESULT = "supply,";
    private static final String RESULT = "result,";

    public void getStatistic(String fromFileName, String toFileName) {
        Path path = Path.of(fromFileName);
        List<String> lines;
        try {
            lines = Files.readAllLines(path);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        String result = calculateStat(lines);
        writeToFile(result, toFileName);
    }

    private String calculateStat(List<String> lines) {
        int supply = 0;
        int buy = 0;
        for (String line : lines) {
            if (line.contains(SUPPLY)) {
                supply += Integer.parseInt(line.split(",")[1]);
            }
            if (line.contains(BUY)) {
                buy += Integer.parseInt(line.split(",")[1]);
            }
        }
        int resultOfBuy = supply - buy;
        return SUPPLIES_RESULT + supply + "\n" + BUY_RESULT + buy + "\n" + RESULT + resultOfBuy;
    }

    private void writeToFile(String resource, String toFileName) {
        try (FileWriter fileWriter = new FileWriter(toFileName)) {
            fileWriter.write(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
