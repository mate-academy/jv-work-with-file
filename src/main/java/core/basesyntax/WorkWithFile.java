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
        writeStatisticToFile(toFileName,readFromFile(fromFileName));
    }

    private void writeStatisticToFile(String toFileName, int[] supplyBuyArray) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, true))) {
            writer.write(reportCreator(supplyBuyArray));
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }

    private String reportCreator(int[] supplyBuyArray) {
        return "supply" + "," + supplyBuyArray[SUPPLY_AMOUNT] + System.lineSeparator()
                + "buy" + "," + supplyBuyArray[BUY_AMOUNT] + System.lineSeparator()
                + "result" + "," + (supplyBuyArray[SUPPLY_AMOUNT] - supplyBuyArray[BUY_AMOUNT]);
    }

    private int[] readFromFile(String fromFileName) {
        int [] supplyBuyArray = new int[2];
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                supplyBuyArray[SUPPLY_AMOUNT] += countTransactions(line)[SUPPLY_AMOUNT];
                supplyBuyArray[BUY_AMOUNT] += countTransactions(line)[BUY_AMOUNT];
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from " + fromFileName, e);
        }
        return supplyBuyArray;
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
