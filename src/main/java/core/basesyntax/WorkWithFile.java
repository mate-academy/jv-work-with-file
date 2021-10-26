package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String report = readStatistic(fromFileName);
        writeStatistic(toFileName, report);
    }

    private String readStatistic(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder textFromFile = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            int value = reader.read();
            while (value != -1) {
                textFromFile.append((char) value);
                value = reader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
        String[] textSplit = textFromFile.toString().split("\r");
        int supply = 0;
        int buy = 0;
        for (String value : textSplit) {
            int index = value.indexOf(',') + 1;
            int length = value.length();
            if (value.contains(SUPPLY)) {
                supply += Integer.parseInt(value.substring(index, length));
            } else if (value.contains(BUY)) {
                buy += Integer.parseInt(value.substring(index, length));
            }
        }
        StringBuilder resultText = new StringBuilder();
        resultText.append(SUPPLY).append(",").append(supply).append(System.lineSeparator())
                .append(BUY).append(",").append(buy).append(System.lineSeparator())
                .append(RESULT).append(",").append(supply - buy);
        return resultText.toString();
    }

    private void writeStatistic(String toFileName, String message) {
        File file = new File(toFileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create file " + toFileName, e);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(message);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }
}
