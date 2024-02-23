package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> lines = readLinesFromFile(fromFileName);
        int supply = 0;
        int buy = 0;

        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts[0].equals(SUPPLY)) {
                supply += Integer.parseInt(parts[1]);
            } else if (parts[0].equals(BUY)) {
                buy += Integer.parseInt(parts[1]);
            }
        }
        int result = supply - buy;
        writeResultToFile(toFileName, supply, buy, result);
    }

    private List<String> readLinesFromFile(String fileName) {
        try {
            return Files.readAllLines(Paths.get(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeResultToFile(String fileName, int supply, int buy, int result) {
        try {
            StringBuilder builder = new StringBuilder();
            builder.append(SUPPLY).append(",").append(supply).append(System.lineSeparator());
            builder.append(BUY).append(",").append(buy).append(System.lineSeparator());
            builder.append(RESULT).append(",").append(result).append(System.lineSeparator());
            String data = builder.toString();

            Files.write(Paths.get(fileName), data.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

