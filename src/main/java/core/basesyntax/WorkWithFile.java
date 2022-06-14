package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int TRANSACTION_TYPE_POSITION = 0;
    private static final int MONEY_AMOUNT_POSITION = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private int supplyAmount = 0;
    private int buyAmount = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        readFromFile(fromFileName);
        writeStatisticToFile(toFileName, getSupplyAmount(), getBuyAmount());
    }

    private void writeStatisticToFile(String toFileName, int supplyAmount, int buyAmount) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, true))) {
            writer.write(SUPPLY + "," + supplyAmount + System.lineSeparator()
                    + BUY + "," + buyAmount + System.lineSeparator()
                    + "result" + "," + (supplyAmount - buyAmount));
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }

    private void readFromFile(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                countTransactions(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from " + fromFileName, e);
        }
    }

    private void countTransactions(String line) {
        String[] transactionType = line.split(",");
        switch (transactionType[TRANSACTION_TYPE_POSITION]) {
            case SUPPLY:
                setSupplyAmount(getSupplyAmount()
                        + Integer.parseInt(transactionType[MONEY_AMOUNT_POSITION]));
                break;
            case BUY:
                setBuyAmount(getBuyAmount()
                        + Integer.parseInt(transactionType[MONEY_AMOUNT_POSITION]));
                break;
            default:
                System.out.println("Invalid transaction type");
        }
    }

    public int getSupplyAmount() {
        return supplyAmount;
    }

    public void setSupplyAmount(int supplyAmount) {
        this.supplyAmount = supplyAmount;
    }

    public int getBuyAmount() {
        return buyAmount;
    }

    public void setBuyAmount(int buyAmount) {
        this.buyAmount = buyAmount;
    }
}
