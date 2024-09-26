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
    private int amountBuy = 0;
    private int amountSupply = 0;
    private int result;

    public void getStatistic(String fromFileName, String toFileName) {
        amountBuy = 0;
        amountSupply = 0;
        result = 0;

        readFile(fromFileName);
        calculateResult();
        writeIntoFile(toFileName);
    }

    public void readFile(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(DELIMITER);
                String operationType = parts[0].trim();
                int amount = Integer.parseInt(parts[1].trim());

                if (operationType.equals(BUY)) {
                    amountBuy += amount;
                } else if (operationType.equals(SUPPLY)) {
                    amountSupply += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }
    }

    public void calculateResult() {
        result = amountSupply - amountBuy;
    }

    private void writeIntoFile(String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(new StringBuilder().append("supply,").append(amountSupply)
                    .append(System.lineSeparator()).toString());
            bufferedWriter.write(new StringBuilder().append("buy,").append(amountBuy)
                    .append(System.lineSeparator()).toString());
            bufferedWriter.write(new StringBuilder().append("result,").append(result).toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file", e);
        }
    }
}
