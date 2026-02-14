package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    private static final int TYPE = 0;
    private static final int AMOUNT = 1;
    private static final String SUPPLY_STRING = "supply";
    private static final String BUY_STRING = "buy";
    private static final String RESULT = "result";
    private static final int VALUES_ARRAY_LENGTH = 3;
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;
    private static final int RESULT_INDEX = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> stringsFromFile = readFile(fromFileName);
        int[] values = calculateTotals(stringsFromFile);
        writeOutput(toFileName, values[SUPPLY_INDEX], values[BUY_INDEX], values[RESULT_INDEX]);
    }

    private static List<String> readFile(String fromFileName) {
        Path fileInput = Path.of(fromFileName);
        try {
            return Files.readAllLines(fileInput);
        } catch (IOException e) {
            throw new RuntimeException("Cant read file.", e);
        }
    }

    private static int[] calculateTotals(List<String> lines) {
        int[] values = new int[VALUES_ARRAY_LENGTH];

        for (String line : lines) {
            String[] parsed = line.split(",");
            if (SUPPLY_STRING.equals(parsed[TYPE])) {
                values[SUPPLY_INDEX] += Integer.parseInt(parsed[AMOUNT]);
            } else {
                values[BUY_INDEX] += Integer.parseInt(parsed[AMOUNT]);
            }
        }
        values[RESULT_INDEX] = values[SUPPLY_INDEX] - values[BUY_INDEX];
        return values;
    }

    private static void writeOutput(String toFileName, int supply, int buy, int result) {
        Path outputFile = Path.of(toFileName);
        try {
            Files.writeString(outputFile, SUPPLY_STRING + "," + supply + System.lineSeparator()
                    + BUY_STRING + "," + buy + System.lineSeparator()
                    + RESULT + "," + result);
        } catch (IOException e) {
            throw new RuntimeException("Cant write to the file.", e);
        }
    }
}
