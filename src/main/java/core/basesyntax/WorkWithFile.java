package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private int sumSupply = 0;
    private int sumBuy = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        int[] results = getValue(fromFileName);
        writeToFile(toFileName, results);
    }

    private int[] getValue(String fromFileName) {
        List<String> strings;
        try {
            strings = Files.readAllLines(new File(fromFileName).toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
        for (String line : strings) {
            int value = Integer.parseInt(line.split(",")[1]);
            if (line.contains("buy")) {
                sumBuy += value;
            } else {
                sumSupply += value;
            }
        }
        return new int[]{sumBuy, sumSupply};
    }

    private void writeToFile(String toFileName, int[] base) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + sumSupply);
            writer.newLine();
            writer.write("buy," + sumBuy);
            writer.newLine();
            writer.write("result," + (sumSupply - sumBuy));
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }
}
