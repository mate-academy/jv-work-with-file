package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int TRANSACTION_TYPE_POSITION = 0;
    private static final int SUPPLY_AMOUNT = 0;
    private static final int BUY_AMOUNT = 1;
    private static final int MONEY_AMOUNT_POSITION = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        writeStatisticToFile(toFileName,
                readFromFile(fromFileName)[SUPPLY_AMOUNT],
                readFromFile(fromFileName)[BUY_AMOUNT]);
    }

    private void writeStatisticToFile(String toFileName, int supplyAmount, int buyAmount) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, true))) {
            writer.write("supply" + "," + supplyAmount + System.lineSeparator()
                    + "buy" + "," + buyAmount + System.lineSeparator()
                    + "result" + "," + (supplyAmount - buyAmount));
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }

    private int[] readFromFile(String fromFileName) {
        int [] supplyBuyCounter = new int[2];
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                supplyBuyCounter[SUPPLY_AMOUNT] += countTransactions(line)[SUPPLY_AMOUNT];
                supplyBuyCounter[BUY_AMOUNT] += countTransactions(line)[BUY_AMOUNT];
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from " + fromFileName, e);
        }
        return supplyBuyCounter;
    }

    private int[] countTransactions(String line) {
        int supplyAmount = 0;
        int buyAmount = 0;
        String[] transactionType = line.split(",");
        switch (transactionType[TRANSACTION_TYPE_POSITION]) {
            case "supply":
                supplyAmount += Integer.parseInt(transactionType[MONEY_AMOUNT_POSITION]);
                break;
            case "buy":
                buyAmount += Integer.parseInt(transactionType[MONEY_AMOUNT_POSITION]);
                break;
            default:
                throw new RuntimeException("Invalid transaction type");
        }
        return new int[]{supplyAmount,buyAmount};
    }
}
