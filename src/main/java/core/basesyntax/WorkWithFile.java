package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    private static final int FIRST_INDEX = 0;
    private static final int SECOND_INDEX = 1;
    private static final String SEPARATOR = ",";
    private static final String BUY_WORD = "buy";
    private static final String SUPPLY_WORD = "supply";
    private static final String RESULT_WORD = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;

        String[] lines = readFile(fromFileName);

        for (String line : lines) {
            String[] data = line.split(SEPARATOR);
            String operation = data[FIRST_INDEX].trim();
            int amount = Integer.parseInt(data[SECOND_INDEX].trim());

            if (operation.equals(SUPPLY_WORD)) {
                supply += amount;
            } else if (operation.equals(BUY_WORD)) {
                buy += amount;
            }
        }
        writeToFile(toFileName, buy, supply);
    }

    private String[] readFile(String fromFileName) {
        try {
            return Files.readString(Path.of(fromFileName)).split(System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Can't access the data", e);
        }
    }

    private String generateReport(int buy, int supply) {
        int result = supply - buy;
        String supplyResult = SUPPLY_WORD + SEPARATOR + supply + System.lineSeparator();
        String buyResult = BUY_WORD + SEPARATOR + buy + System.lineSeparator();
        String resultResult = RESULT_WORD + SEPARATOR + result + System.lineSeparator();
        return supplyResult + buyResult + resultResult;
    }

    private void writeToFile(String toFileName, int buy, int supply) {
        try {
            Files.writeString(Path.of(toFileName), generateReport(buy, supply));
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
