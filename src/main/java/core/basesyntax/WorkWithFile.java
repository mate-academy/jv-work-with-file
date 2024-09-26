package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String DELIMITER = ",";
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final int INDEX_AMOUNT_BUY = 0;
    private static final int INDEX_AMOUNT_SUPPLY = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int[] results = readFile(fromFileName);
        writeIntoFile(toFileName, results);
    }

    private int[] readFile(String fromFileName) {
        int amountBuy = 0;
        int amountSupply = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(DELIMITER);
                String operationType = parts[0].trim();
                int amount = Integer.parseInt(parts[1].trim());

                int[] updatedAmounts = processOperation(operationType,
                        amount, amountBuy, amountSupply);
                amountBuy = updatedAmounts[INDEX_AMOUNT_BUY];
                amountSupply = updatedAmounts[INDEX_AMOUNT_SUPPLY];
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file: " + fromFileName, e);
        }

        return calculateResult(amountSupply, amountBuy);
    }

    private int[] processOperation(String operationType,
                                   int amount, int amountBuy, int amountSupply) {
        if (operationType.equals(BUY)) {
            amountBuy += amount;
        } else if (operationType.equals(SUPPLY)) {
            amountSupply += amount;
        }
        return new int[]{amountBuy, amountSupply};
    }

    private int[] calculateResult(int amountSupply, int amountBuy) {
        int result = amountSupply - amountBuy;
        return new int[]{amountSupply, amountBuy, result};
    }

    private void writeIntoFile(String toFileName, int[] results) {
        String report = prepareReport(results);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file: " + toFileName, e);
        }
    }

    private String prepareReport(int[] results) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supply,").append(results[0]).append(System.lineSeparator());
        stringBuilder.append("buy,").append(results[1]).append(System.lineSeparator());
        stringBuilder.append("result,").append(results[2]).append(System.lineSeparator());
        return stringBuilder.toString();
    }

}
