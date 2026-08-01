package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String content = readFromFile(fromFileName);
        String report = createReport(content);
        writeToFile(toFileName, report);
    }

    private String readFromFile(String fileName) {
        try {
            return Files.readString(Path.of(fileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file!", e);
        }
    }

    private String createReport(String content) {
        String[] lines = content.split("\\R");
        int supply = 0;
        int buy = 0;

        for (String line : lines) {
            String[] parts = line.split(",");
            String operation = parts[0];
            int amount = Integer.parseInt(parts[1]);

            if (SUPPLY.equals(operation)) {
                supply += amount;
            }

            if (BUY.equals(operation)) {
                buy += amount;
            }
        }

        return SUPPLY + "," + supply + System.lineSeparator()
                + BUY + "," + buy + System.lineSeparator()
                + RESULT + "," + (supply - buy);
    }

    private void writeToFile(String fileName, String report) {
        try {
            Files.writeString(Path.of(fileName), report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file!", e);
        }
    }
}
