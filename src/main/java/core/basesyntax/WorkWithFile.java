package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WorkWithFile {
    private static final String SPLITTER = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final int NAME_INDEX = 0;
    private static final int VALUE_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {

        String[] splittedText = readFile(fromFileName);

        String report = generateReport(splittedText);

        writeFromFile(toFileName, report);
    }

    private String[] readFile(String fromFileName) {
        try {
            String text = new String(Files.readAllBytes(Paths.get(fromFileName)));

            String[] splittedText = text.split(System.lineSeparator());
            return splittedText;
        } catch (IOException e) {
            throw new RuntimeException("Can`t read the file", e);
        }
    }

    private String generateReport(String[] splittedTextFromFile) {
        StringBuilder textToWrite = new StringBuilder();
        int supplyCount = 0;
        int buyCount = 0;
        int result;

        for (String line : splittedTextFromFile) {
            String[] split = line.split(SPLITTER);

            if (split[NAME_INDEX].equals(SUPPLY)) {
                supplyCount += Integer.parseInt(split[VALUE_INDEX]);
            } else if (split[NAME_INDEX].equals(BUY)) {
                buyCount += Integer.parseInt(split[VALUE_INDEX]);
            }
        }

        result = supplyCount - buyCount;

        textToWrite.append(SUPPLY)
                .append(SPLITTER)
                .append(supplyCount)
                .append(System.lineSeparator())
                .append(BUY)
                .append(SPLITTER)
                .append(buyCount)
                .append(System.lineSeparator())
                .append(RESULT)
                .append(SPLITTER)
                .append(result);

        return textToWrite.toString();
    }

    private void writeFromFile(String toFileName, String textToWrite) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(textToWrite.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file", e);
        }
    }
}
