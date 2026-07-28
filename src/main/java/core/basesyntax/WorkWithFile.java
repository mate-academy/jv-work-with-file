package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class WorkWithFile {
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> data = readFromFile(fromFileName);
        List<String> report = createReport(data);
        writeToFile(toFileName, report);
    }

    private List<String> readFromFile(String fromFileName) {
        try {
            return Files.readAllLines(Paths.get(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fromFileName, e);
        }
    }

    private List<String> createReport(List<String> data) {
        int supply = 0;
        int buy = 0;

        for (String line : data) {
            String[] parts = line.split(COMMA);
            int amount = Integer.parseInt(parts[AMOUNT_INDEX]);

            if (SUPPLY.equals(parts[OPERATION_INDEX])) {
                supply += amount;
            } else if (BUY.equals(parts[OPERATION_INDEX])) {
                buy += amount;
            }
        }

        int result = supply - buy;

        return List.of(
                SUPPLY + COMMA + supply,
                BUY + COMMA + buy,
                RESULT + COMMA + result
        );
    }

    private void writeToFile(String toFileName, List<String> report) {
        try {
            Files.write(Paths.get(toFileName), report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file: " + toFileName, e);
        }
    }
}
