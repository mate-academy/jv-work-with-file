package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    private static final String COMMA = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int[] statistic = readStatistic(fromFileName);
        String report = createReport(statistic[0], statistic[1]);
        writeReport(toFileName, report);
    }

    private int[] readStatistic(String fromFileName) {
        int supplySum = 0;
        int buySum = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line = reader.readLine();

            while (line != null) {
                String[] data = line.split(COMMA);
                String operation = data[OPERATION_INDEX];
                int amount = Integer.parseInt(data[AMOUNT_INDEX]);

                if (operation.equals(SUPPLY)) {
                    supplySum += amount;
                } else if (operation.equals(BUY)) {
                    buySum += amount;
                }

                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file " + fromFileName, e);
        }

        return new int[]{supplySum, buySum};
    }

    private String createReport(int supplySum, int buySum) {
        return SUPPLY + COMMA + supplySum + System.lineSeparator()
                + BUY + COMMA + buySum + System.lineSeparator()
                + RESULT + COMMA + (supplySum - buySum);
    }

    private void writeReport(String toFileName, String report) {
        try {
            Files.writeString(Path.of(toFileName), report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName, e);
        }
    }
}
