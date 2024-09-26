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
                amountBuy = updatedAmounts[0];
                amountSupply = updatedAmounts[1];
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
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write("supply," + results[0] + System.lineSeparator());
            bufferedWriter.write("buy," + results[1] + System.lineSeparator());
            bufferedWriter.write("result," + results[2]);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file: " + toFileName, e);
        }
    }
}
