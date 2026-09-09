package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> lines = readFromFile(fromFileName);
        String report = createReport(lines);
        writeToFile(toFileName, report);
    }

    private List<String> readFromFile(String fileName) {
        try {
            return Files.readAllLines(Path.of(fileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file!", e);
        }
    }

    private String createReport(List<String> lines) {
        int supplyTotal = 0;
        int buyTotal = 0;

        for (String line : lines) {
            String[] split = line.split(",");
            if (split[0].equals(SUPPLY)) {
                supplyTotal += Integer.parseInt(split[1]);
            } else if (split[0].equals(BUY)) {
                buyTotal += Integer.parseInt(split[1]);
            }
        }

        return SUPPLY + "," + supplyTotal
                + System.lineSeparator()
                + BUY + "," + buyTotal
                + System.lineSeparator()
                + "result," + (supplyTotal - buyTotal);
    }

    private void writeToFile(String fileName, String report) {
        try {
            Files.writeString(Path.of(fileName), report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file!", e);
        }
    }
}
