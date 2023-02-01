package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private StringBuilder builder = new StringBuilder();

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromFile = readDataFile(fromFileName);
        int supply = 0;
        int buy = 0;
        for (String lineInFile : dataFromFile) {
            if (lineInFile.contains("supply")) {
                supply += getAmount(lineInFile);
            } else {
                buy += getAmount(lineInFile);
            }
        }
        builder.setLength(0);
        builder.append("supply,").append(supply).append(LINE_SEPARATOR)
                .append("buy,").append(buy).append(LINE_SEPARATOR)
                .append("result,").append(supply - buy);
        writeDataToFile(toFileName, builder.toString());
        builder.setLength(0);
    }

    private int getAmount(String line) {
        String[] amount = line.split(",");
        return Integer.parseInt(amount[1]);
    }

    private String[] readDataFile(String fromFileName) {
        String lineInFile = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(
                new File(fromFileName)))) {
            lineInFile = reader.readLine();
            while (lineInFile != null) {
                builder.append(lineInFile).append(LINE_SEPARATOR);
                lineInFile = reader.readLine();
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
}

