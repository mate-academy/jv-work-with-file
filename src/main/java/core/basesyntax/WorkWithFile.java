package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    private static final int FIRST_COLUMN_INDEX = 0;
    private static final int SECOND_COLUMN_INDEX = 1;
    private static final String SEPARATOR = ",";
    private static final String BUY_WORD_FOR_COMPARING = "buy";
    private static final String SUPPLY_WORD_FOR_COMPARING = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            int buySum = 0;
            int supplySum = 0;
            while (value != null) {
                String[] splitData = value.split(SEPARATOR);
                if (splitData[FIRST_COLUMN_INDEX].equals(BUY_WORD_FOR_COMPARING)) {
                    buySum += Integer.parseInt(splitData[SECOND_COLUMN_INDEX]);
                } else if (splitData[FIRST_COLUMN_INDEX].equals(SUPPLY_WORD_FOR_COMPARING)) {
                    supplySum += Integer.parseInt(splitData[SECOND_COLUMN_INDEX]);
                }
                value = bufferedReader.readLine();
            }
            writeToFile(toFileName, buySum, supplySum);
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
