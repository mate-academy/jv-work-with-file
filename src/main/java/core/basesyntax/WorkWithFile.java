package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int SPLIT_LINE_STATUS = 0;
    private static final int SPLIT_LINE_VALUE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFromFile(fromFileName);
        String report = createReport(dataFromFile);
        writerToFile(report, toFileName);
    }

    private String readFromFile(String file) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int value = reader.read();
            while (value != -1) {
                builder.append((char) value);
                value = reader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file " + file, e);
        }
        return builder.toString();
    }

    private String createReport(String dataFromFile) {
        String[] lines = dataFromFile.split(System.lineSeparator());
        String[] splitLine;
        StringBuilder builder = new StringBuilder();
        int supply = 0;
        int buy = 0;

        for (String line : lines) {
            splitLine = line.split(",");
            if (splitLine[SPLIT_LINE_STATUS].equals("buy")) {
                buy += Integer.parseInt(splitLine[SPLIT_LINE_VALUE]);
            } else {
                supply += Integer.parseInt(splitLine[SPLIT_LINE_VALUE]);
            }
        }

        builder.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append((supply - buy))
                .append(System.lineSeparator());

        return builder.toString();
    }

    private void writerToFile(String report, String file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + file, e);
        }
    }
}
