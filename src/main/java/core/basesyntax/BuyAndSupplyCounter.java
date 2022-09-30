package core.basesyntax;

public class BuyAndSupplyCounter {
    private final int numberDefault = 0;

    public int countSupply(String[] supplyAmount) {
        String supply = "supply";
        int supp = numberDefault;
        for (String index : supplyAmount) {
            if (index.contains(supply)) {
                supp += Integer.parseInt(index.replaceAll("\\D+", ""));
            }
        }
        return supp;
    }

    public int countBuys(String[] buysAmount) {
        String buy = "buy";
        int buys = numberDefault;
        for (String index : buysAmount) {
            if (index.contains(buy)) {
                buys += Integer.parseInt(index.replaceAll("\\D+", ""));
            }
        }
        return buys;
    }
}
