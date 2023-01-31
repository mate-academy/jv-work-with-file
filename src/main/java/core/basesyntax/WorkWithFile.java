package core.basesyntax;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;

public class WorkWithFile {
    private int supply = 0;
    private int buy = 0;
    StringBuilder builder = new StringBuilder();

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromFile = readDataFile(fromFileName);
        for (String lineInFile : dataFromFile) {
            if (lineInFile.contains("supply")) {
                supply += getAmount(lineInFile);
            } else {
                buy += getAmount(lineInFile);
            }
        }
        builder.setLength(0);
        builder.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy").append(buy).append(System.lineSeparator())
                .append("result,").append(supply - buy);
        writeDataToFile(toFileName, builder.toString());
    }

    public int getAmount(String line) {
        String[] amount = line.split(",");
        return Integer.parseInt(amount[1]);
    }

    public String[] readDataFile(String fromFileName) {
        String lineInFile = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(
                new File(fromFileName)))) {
            lineInFile = reader.readLine();
            while (lineInFile != null) {
                builder.append(lineInFile).append(System.lineSeparator());
                lineInFile = reader.readLine();
            }
        } catch (IOException exception) {
            throw new RuntimeException("File don't read", exception);
        }
        return builder.toString().split(System.lineSeparator());
    }

    public void writeDataToFile(String toFileName, String result) {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(new File(toFileName)))) {
            writer.write(result);
        } catch (IOException exception) {
            throw new RuntimeException("File don't write", exception);
        }
    }
}

