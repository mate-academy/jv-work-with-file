package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;

public class WorkWithFile {
    private static final String DATA_SEPARATOR = ",";
    private static final int ENTRY_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String SUPPLY_COMPARE = "supply";
    private static final String SUPPLY_LITERAL = "supply,";
    private static final String BUY_LITERAL = "buy,";
    private static final String RESULT_LITERAL = "result,";

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        for (String line : readFromFile(fromFileName)) {
            String[] entryType = line.split(DATA_SEPARATOR);
            int amount = Integer.parseInt(entryType[AMOUNT_INDEX]);
            if (Objects.equals(entryType[ENTRY_TYPE_INDEX], SUPPLY_COMPARE)) {
                supply += amount;
            } else {
                buy += amount;
            }
        }
        writeToFile(generateString(supply, buy), toFileName);
    }

    private List<String> readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        try {
            return Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Check your file " + fromFileName, e);
        }
    }

    private String generateString(int supply, int buy) {
        return SUPPLY_LITERAL + supply + System.lineSeparator()
                + BUY_LITERAL + buy + System.lineSeparator()
                + RESULT_LITERAL + (supply - buy);
    }

    private void writeToFile(String text, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(text);
        } catch (IOException e) {
            throw new RuntimeException("Unable to write " + toFileName, e);
        }
    }
}
