package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    private static final String COMMA = ",";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {

        final int SupplyIndex = 0;
        final int BuyIndex = 1;

        int[] totals = readFromFile(fromFileName);
        String report = generateReport(totals[SupplyIndex], totals[BuyIndex]);
        writeToFile(toFileName, report);
    }

    private static int[] readFromFile(String fromFileName) {

        int supplyTotal = 0;
        int buyTotal = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(COMMA);
                String operationType = parts[OPERATION_TYPE_INDEX];
                int amount = Integer.parseInt(parts[AMOUNT_INDEX]);

                if (operationType.equals(SUPPLY)) {
                    supplyTotal += amount;
                } else if (operationType.equals(BUY)) {
                    buyTotal += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("An error occurred while reading the file "
                    + fromFileName, e);
        }

        return new int[]{supplyTotal, buyTotal};
    }

    private static String generateReport(int supplyTotal, int buyTotal) {

        int result = supplyTotal - buyTotal;

        StringBuilder report = new StringBuilder();
        report.append(SUPPLY).append(" ").append(supplyTotal).append(System.lineSeparator());
        report.append(BUY).append(" ").append(buyTotal).append(System.lineSeparator());
        report.append(RESULT).append(" ").append(result).append(System.lineSeparator());

        return report.toString();
    }

    private static void writeToFile(String toFileName, String report) {
        try {
            Files.writeString(Path.of(toFileName), report);
        } catch (IOException e) {
            throw new RuntimeException("An error occurred while writing the file "
                    + toFileName, e);
        }
    }
}
