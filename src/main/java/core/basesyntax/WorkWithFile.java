package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
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
        StringBuilder stringBuilder = new StringBuilder();
        int supply = 0;
        int buy = 0;
        try {
            stringBuilder.append(Files.readAllLines(fromFile.toPath()));
            String[] readedLines = stringBuilder.toString().split("\\W++");
            for (int i = 0; i < readedLines.length; i++) {
                if (readedLines[i].equals(SUPPLY)) {
                    supply += Integer.parseInt(readedLines[i + 1]);
                } else if (readedLines[i].equals(BUY)) {
                    buy += Integer.parseInt(readedLines[i + 1]);
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
