package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    private static final int KEY_INDEX = 0;
    private static final int VALUE_INDEX = 1;
    private static final String SUPPLY_KEY = "supply";
    private static final String BUY_KEY = "buy";
    private static final String RESULT_KEY = "result";
    private static final String CSV_SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> content = readFileLines(fromFileName);
        int supply = 0;
        int buy = 0;

        for (String line : content) {
            String[] lineParts = line.split(CSV_SEPARATOR);

            if (lineParts.length == 2) {
                String key = lineParts[KEY_INDEX];
                int value = Integer.parseInt(lineParts[VALUE_INDEX]);

                switch (key) {
                    case SUPPLY_KEY:
                        supply += value;
                        break;
                    case BUY_KEY:
                        buy += value;
                        break;
                    default:
                        break;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(SUPPLY_KEY + CSV_SEPARATOR).append(supply).append(System.lineSeparator());
        sb.append(BUY_KEY + CSV_SEPARATOR).append(buy).append(System.lineSeparator());
        sb.append(RESULT_KEY + CSV_SEPARATOR).append(supply - buy);

        writeToFile(toFileName, sb.toString());
    }

    private List<String> readFileLines(String fileName) {
        try {
            return Files.readAllLines(Path.of(fileName));
        } catch (IOException e) {
            throw new RuntimeException("Cannot read from file " + fileName, e);
        }
    }

    private void writeToFile(String fileName, String content) {
        try {
            Files.writeString(Path.of(fileName), content);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write to file " + fileName, e);
        }
    }
}
