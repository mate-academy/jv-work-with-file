package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        String[] dataToWrite = new String[3];
        StringBuilder stringBuilder = new StringBuilder();
        int supply = 0;
        int buy = 0;
        try {
            stringBuilder.append(Files.readAllLines(fromFile.toPath()));
            String[] readedLines = stringBuilder.toString().split("\\W++");

            for (int i = 0; i < readedLines.length; i++) {
                if (i >= 1 && readedLines[i - 1].contains(SUPPLY)) {
                    supply += Integer.parseInt(readedLines[i]);
                } else if (i >= 1 && readedLines[i - 1].contains(BUY)) {
                    buy += Integer.parseInt(readedLines[i]);
                }
            }
            writeToFile(toFileName, getResult(supply, buy));
        } catch (IOException e) {
            throw new RuntimeException("Error while reading file ", e);
        }
    }

    private String getResult(int supply, int buy) {
        StringBuilder builder = new StringBuilder();
        builder.append(SUPPLY).append(COMMA).append(supply).append(System.lineSeparator())
                .append(BUY).append(COMMA).append(buy).append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(supply - buy);
        return builder.toString();
    }

    private void writeToFile(String fileName, String dataToSave) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
                writer.write(dataToSave);
        } catch (IOException e) {
            throw new RuntimeException("Error while writing data ", e);
        }
    }
}
