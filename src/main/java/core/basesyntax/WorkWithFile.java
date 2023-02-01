package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromFile = readDataFile(fromFileName);
        String report = createReport(dataFromFile);
        writeDataToFile(toFileName, report);
    }

    private int getAmount(String line) {
        String[] amount = line.split(",");
        return Integer.parseInt(amount[AMOUNT_INDEX]);
    }

    private String[] readDataFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        String line = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(
                new File(fromFileName)))) {
            line = reader.readLine();
            while (line != null) {
                builder.append(line).append(LINE_SEPARATOR);
                line = reader.readLine();
            }
        } catch (IOException exception) {
            throw new RuntimeException("File couldn't be read: " + fromFileName, exception);
        }
        return builder.toString().split(LINE_SEPARATOR);
    }

    private void writeDataToFile(String toFileName, String result) {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(new File(toFileName)))) {
            writer.write(result);
        } catch (IOException exception) {
            throw new RuntimeException("Couldn't write the file: " + toFileName, exception);
        }
    }

    private String createReport(String[] data) {
        StringBuilder builder = new StringBuilder();
        int supply = 0;
        int buy = 0;
        for (String line : data) {
            if (line.contains("supply")) {
                supply += getAmount(line);
            } else {
                buy += getAmount(line);
            }
        }
        builder.append("supply,").append(supply).append(LINE_SEPARATOR)
                .append("buy,").append(buy).append(LINE_SEPARATOR)
                .append("result,").append(supply - buy);
        return builder.toString();
    }
}

