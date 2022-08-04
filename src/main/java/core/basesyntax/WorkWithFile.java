package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final String COMA = ",";
    private static final int NUMBER = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int[] results = getValue(fromFileName);
        writeToFile(toFileName, results);
    }

    private int[] getValue(String fromFileName) {
        int sumSupply = 0;
        int sumBuy = 0;
        List<String> strings;
        try {
            strings = Files.readAllLines(new File(fromFileName).toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
        for (String line : strings) {
            int value = Integer.parseInt(line.split(COMA)[NUMBER]);
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
            writer.write("supply," + base[1]);
            writer.newLine();
            writer.write("buy," + base[0]);
            writer.newLine();
            writer.write("result," + (base[1] - base[0]));
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }
}
