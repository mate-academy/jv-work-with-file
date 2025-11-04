package core.basesyntax;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final int AMOUNT_POSITION = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> lines = readInfoFromFile(fromFileName);
        StringBuilder statistic = new StringBuilder();
        int buy = 0;
        int supply = 0;
        for (String line : lines) {
            String[] value = line.split(",");
            if (line.contains("supply")) {
                supply += Integer.parseInt(value[AMOUNT_POSITION]);
            } else {
                buy += Integer.parseInt(value[AMOUNT_POSITION]);
            }
        }
        statistic.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(supply - buy);
        writeInfoToFile(toFileName, statistic.toString());
    }

    public List<String> readInfoFromFile(String fromFileName) {
        File fromFile = new File(fromFileName);
        List<String> lines;
        try {
            lines = Files.readAllLines(fromFile.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can`t read data from file " + fromFileName, e);
        }
        return lines;
    }

    public void writeInfoToFile(String toFileName, String data) {
        File file = new File(toFileName);
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName, e);
        }
    }
}
