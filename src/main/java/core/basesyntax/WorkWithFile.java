package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> data = readFile(fromFileName);
        String report = createReport(data);
        writeFile(toFileName, report);
    }

    private List<String> readFile(String fileName) {
        try {
            return Files.readAllLines(Path.of(fileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fileName, e);
        }
    }

    private String createReport(List<String> lines) {
        int supplySum = 0;
        int buySum = 0;

        for (String line : lines) {
            String[] splitLine = line.split(",");
            int amount = Integer.parseInt(splitLine[AMOUNT_INDEX]);

            if (splitLine[OPERATION_TYPE_INDEX].equals(SUPPLY)) {
                supplySum += amount;
            } else if (splitLine[OPERATION_TYPE_INDEX].equals(BUY)) {
                buySum += amount;
            }
        }

        int result = supplySum - buySum;
        return SUPPLY + "," + supplySum + System.lineSeparator()
                + BUY + "," + buySum + System.lineSeparator()
                + "result," + result;
    }

    private void writeFile(String fileName, String report) {
        try {
            Files.writeString(Path.of(fileName), report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file: " + fileName, e);
        }
    }
}
