package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final int FIRST_COLUMN_INDEX = 0;
    private static final int SECOND_COLUMN_INDEX = 1;
    private static final String SEPARATOR = ",";
    private static final String BUY_WORD_FOR_COMPARING = "buy";
    private static final String SUPPLY_WORD_FOR_COMPARING = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> data = readFromFile(fromFileName);
        int buySum = 0;
        int supplySum = 0;
        for (String line : data) {
            String[] splitData = line.split(SEPARATOR);
            if (splitData[FIRST_COLUMN_INDEX].equals(BUY_WORD_FOR_COMPARING)) {
                buySum += Integer.parseInt(splitData[SECOND_COLUMN_INDEX]);
            } else if (splitData[FIRST_COLUMN_INDEX].equals(SUPPLY_WORD_FOR_COMPARING)) {
                supplySum += Integer.parseInt(splitData[SECOND_COLUMN_INDEX]);
            }
        }
        writeToFile(toFileName, buySum, supplySum);
    }

    private List<String> readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        try {
            return Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't access the data", e);
        }

    }

    private void writeToFile(String toFileName, int buySum, int supplySum) {
        File fileToWrite = new File(toFileName);
        try {
            Files.write(fileToWrite.toPath(), createReport(buySum, supplySum).getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }

    private String createReport(int buySum, int supplySum) {
        return
                String.format("supply,%d" + System.lineSeparator()
                        + "buy,%d" + System.lineSeparator()
                        + "result,%d", supplySum, buySum, supplySum - buySum);
    }
}
