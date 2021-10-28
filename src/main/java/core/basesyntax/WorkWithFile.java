package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final int INDEX = 0;
    private static final int SUPPLY_INDEX = 0;
    private static final String BUY = "buy";
    private static final int BUY_INDEX = 1;
    private static final String RESULT = "result";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] statistic = readLines(fromFileName);
        writeToFile(toFileName, getResult(statistic[SUPPLY_INDEX], statistic[BUY_INDEX]));
    }

    private int[] readLines(String fileName) {
        File fromFile = new File(fileName);
        int supply = 0;
        int buy = 0;
        try {
            List<String> read = Files.readAllLines(fromFile.toPath());
            for (String readedLines : read) {
                String[] splitter = readedLines.split(",");
                if (splitter[INDEX].equals(SUPPLY)) {
                    supply += Integer.parseInt(splitter[1]);
                } else if (splitter[INDEX].equals(BUY)) {
                    buy += Integer.parseInt(splitter[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while reading file ", e);
        }
        return new int[]{supply, buy};
    }

    private String getResult(int supply, int buy) {
        StringBuilder builder = new StringBuilder();
        return builder.append(SUPPLY).append(COMMA).append(supply).append(System.lineSeparator())
                .append(BUY).append(COMMA).append(buy).append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(supply - buy).toString();
    }

    private void writeToFile(String fileName, String dataToSave) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(dataToSave);
        } catch (IOException e) {
            throw new RuntimeException("Error while writing data ", e);
        }
    }
}
