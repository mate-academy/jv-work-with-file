package core.basesyntax;

public class BuyAndSupplyCounter {

    public int countSupply(String[] supplyAmount) {
        int supp = WorkWithFile.DEFAULT_NUMBER;
        for (String index : supplyAmount) {
            if (index.contains(WorkWithFile.SUPPLY)) {
                supp += Integer.parseInt(index.replaceAll("\\D+", ""));
            }
        }
        return supp;
    }

    public int countBuys(String[] buysAmount) {
        int buys = WorkWithFile.DEFAULT_NUMBER;
        for (String index : buysAmount) {
            if (index.contains(WorkWithFile.BUY)) {
                buys += Integer.parseInt(index.replaceAll("\\D+", ""));
            }
        }
        return buys;
    }
}
