package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WorkWithFile {
    private static final int WORD_INDEX = 0;
    private static final int NUMBER_INDEX = 1;
    private static final String SEPARATOR = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFile(fromFileName);
        String report = generateReport(data);
        writeToFile(toFileName, report);
    }

    private String readFile(String fromFileName) {
        try {
            return new String(Files.readAllBytes(Paths.get(fromFileName)));
        } catch (IOException e) {
            throw new RuntimeException("Error while reading file: " + fromFileName, e);
        }
    }

    private String generateReport(String data) {
        int supplySum = 0;
        int buySum = 0;

        String[] lines = data.split(System.lineSeparator());
        for (String line : lines) {
            String[] parts = line.split(SEPARATOR);
            int extractedNumber = Integer.parseInt(parts[NUMBER_INDEX]);

            if (parts[WORD_INDEX].equals(SUPPLY)) {
                supplySum += extractedNumber;
            } else if (parts[WORD_INDEX].equals(BUY)) {
                buySum += extractedNumber;
            }
        }

        int result = supplySum - buySum;
        return SUPPLY + SEPARATOR + supplySum + System.lineSeparator()
                + BUY + SEPARATOR + buySum + System.lineSeparator()
                + RESULT + SEPARATOR + result;
    }

    private void writeToFile(String toFileName, String report) {
        try {
            Files.write(Paths.get(toFileName), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Error while writing report to file: " + toFileName, e);
        }
    }
}
