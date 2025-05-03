package core.basesyntax.suppliers;

import java.util.ArrayList;

public class AmountSupplier {
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int START_AMOUNT = 0;
    private static final int AMOUNT_INDEX = 1;

    public int getTotalBuyAmount(ArrayList<String[]> data) {
        int amount = START_AMOUNT;
        for (String[] datum : data) {
            if (datum[OPERATION_TYPE_INDEX].equals("buy")) {
                amount += Integer.parseInt(datum[AMOUNT_INDEX]);
            }
        }
        return amount;
    }

    public int getTotalSupplyAmount(ArrayList<String[]> data) {
        int amount = START_AMOUNT;
        for (String[] datum : data) {
            if (datum[OPERATION_TYPE_INDEX].equals("supply")) {
                amount += Integer.parseInt(datum[AMOUNT_INDEX]);
            }
        }
        return amount;
    }

    public int getResultAmount(ArrayList<String[]> data) {
        return getTotalSupplyAmount(data) - getTotalBuyAmount(data);
    }
}
