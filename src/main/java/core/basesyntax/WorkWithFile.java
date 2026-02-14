package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    private static final int TYPE = 0;
    private static final int AMOUNT = 1;
    private static final String SUPPLY = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> stringsFromFile = readFile(fromFileName);
        int supply = 0;
        int buy = 0;

        for (String line : stringsFromFile) {
            String[] parsed = line.split(",");
            if (SUPPLY.equals(parsed[TYPE])) {
                supply += Integer.parseInt(parsed[AMOUNT]);
            } else {
                buy += Integer.parseInt(parsed[AMOUNT]);
            }
        }
        writeOutput(toFileName, supply, buy);
    }

    private static List<String> readFile(String fromFileName) {
        Path fileInput = Path.of(fromFileName);
        try {
            return Files.readAllLines(fileInput);
        } catch (IOException e) {
            throw new RuntimeException("Cant read file.", e);
        }
    }

    private static void writeOutput(String toFileName, int supply, int buy) {
        Path outputFile = Path.of(toFileName);
        int result = supply - buy;

        try {
            Files.writeString(outputFile, "supply," + supply + System.lineSeparator()
                    + "buy," + buy + System.lineSeparator()
                    + "result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Cant write to the file.", e);
        }
    }
}
