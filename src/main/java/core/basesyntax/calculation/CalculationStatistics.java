package core.basesyntax.calculation;

import java.util.ArrayList;

public class CalculationStatistics {
    private static final String BUY_OPERATION = "buy";
    private static final String SUPPLY_OPERATION = "supply";

    public static String getNumber(ArrayList<String> str) {
        int supplyOperation = 0;
        int buyOperation = 0;
        for (String strO : str) {
            if (strO.trim().startsWith(SUPPLY_OPERATION)) {
                supplyOperation += Integer.parseInt(strO.substring(strO.indexOf(',') + 1));
            } else if (strO.trim().startsWith(BUY_OPERATION)) {
                buyOperation += Integer.parseInt(strO.substring(strO.indexOf(',') + 1));
            } else {
                break;
            }
        }
        return "supply," + supplyOperation + System.lineSeparator()
                + "buy," + buyOperation + System.lineSeparator()
                + "result," + (supplyOperation - buyOperation);
    }
}
