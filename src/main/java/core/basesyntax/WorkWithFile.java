package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    private static final int KEY_IDX = 0;
    private static final int VALUE_IDX = 1;
    private static final String SUPPLY_KEY = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFile(fromFileName);
        String report = createReport(data);
        writeFile(toFileName, report);
    }

    private String readFile(String fromFileName) {
        Path path = Path.of(fromFileName);
        try {
            return Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }
    }

    private String createReport(String data) {
        int supply = 0;
        int buy = 0;
        String[] splitData = data.split("\n");
        String[] line;
        for (String entry : splitData) {
            line = entry.split(",");
            if (line[KEY_IDX].equals(SUPPLY_KEY)) {
                supply += Integer.parseInt(line[VALUE_IDX]);
            } else {
                buy += Integer.parseInt(line[VALUE_IDX]);
            }
        }

        return String.format("supply,%d" + System.lineSeparator()
                + "buy,%d" + System.lineSeparator() + "result,%d",
                supply, buy, supply - buy);
    }

    private void writeFile(String toFileName, String toWrite) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(toWrite);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
