package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";
    public static final String RESULT = "result";
    public static final int OPERATION_TYPE_INDEX = 0;
    public static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int supplyAmount = 0;
        int buyAmount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            while (reader.ready()) {
                String line = reader.readLine();
                String[] lineSplitted = line.split(",");
                String operationType = lineSplitted[OPERATION_TYPE_INDEX];
                int amount = Integer.parseInt(lineSplitted[AMOUNT_INDEX]);
                if (operationType.equals(SUPPLY)) {
                    supplyAmount += amount;
                } else {
                    buyAmount += amount;
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String statistic = getStatistic(supplyAmount, buyAmount);
        write(statistic, toFileName);
    }

    private String getStatistic(int supplyAmount, int buyAmount) {
        int resultAmount = supplyAmount - buyAmount;
        StringBuilder statistic = new StringBuilder(SUPPLY)
                .append(",")
                .append(supplyAmount)
                .append(System.lineSeparator())
                .append(BUY)
                .append(",")
                .append(buyAmount)
                .append(System.lineSeparator())
                .append(RESULT)
                .append(",")
                .append(resultAmount);
        return statistic.toString();
    }

    private void write(String statistic, String toFileName) {
        try (FileWriter writer = new FileWriter(toFileName)) {
            writer.write(statistic);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
