package core.basesyntax;

import static core.basesyntax.OperationType.BUY;
import static core.basesyntax.OperationType.SUPPLY;

public class StatisticParser {

    public Statistic parseCsv(Csv csv) {
        Statistic statistic = new Statistic();
        String row = csv.nextRow();
        while (row != null) {
            if (row.startsWith(SUPPLY)) {
                String amountColumn = row.substring(SUPPLY.length() + 1); //skip comma
                statistic.addSupply(Integer.parseInt(amountColumn));
            } else if (row.startsWith(BUY)) {
                String amountColumn = row.substring(BUY.length() + 1); //skip comma
                statistic.addBuy(Integer.parseInt(amountColumn));
            }
            row = csv.nextRow();
        }

        return statistic;
    }
}
