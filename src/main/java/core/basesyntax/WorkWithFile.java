package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    private static final String OPERATION_SUPPLY = "supply";
    private static final String OPERATION_BUY = "buy";
    private static final String RESULT = "result";
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String DELIMITER = ",";
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> lines = readFromFile(fromFileName);
        int[] totals = calculateTotals(lines);
        int result = calculateResult(totals[SUPPLY_INDEX], totals[BUY_INDEX]);
        writeToFile(toFileName, totals[SUPPLY_INDEX], totals[BUY_INDEX], result);
    }

    private List<String> readFromFile(String fromFileName) {
        final Path path = Path.of(fromFileName);
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }
    }

    private int[] calculateTotals(List<String> lines) {
        int operationSupply = 0;
        int operationBuy = 0;

        for (String line : lines) {
            String[] splitLine = line.split(DELIMITER);
            String operation = splitLine[OPERATION_INDEX];
            int amount = Integer.parseInt(splitLine[AMOUNT_INDEX]);

            if (operation.equals(OPERATION_SUPPLY)) {
                operationSupply += amount;
            } else if (operation.equals(OPERATION_BUY)) {
                operationBuy += amount;
            }
        }

        return new int[]{operationSupply, operationBuy};
    }

    private int calculateResult(int operationSupply, int operationBuy) {
        return operationSupply - operationBuy;
    }

    private void writeToFile(String toFileName, int operationSupply,
                             int operationBuy, int calculateResult) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(String.format("%s,%d%n", OPERATION_SUPPLY, operationSupply));
            writer.write(String.format("%s,%d%n", OPERATION_BUY, operationBuy));
            writer.write(String.format("%s,%d", RESULT, calculateResult));
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
