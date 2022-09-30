package core.basesyntax;

public class CountBuysAndSupply {

    private final SplitAllSpaces noSpaces = new SplitAllSpaces();

    public int countSupply() {
        String[] arrayWithoutSpaces = noSpaces.splitSpaces();
        int supp = WorkWithFile.DEFAULT_NUMBER;
        for (String index : arrayWithoutSpaces) {
            if (index.contains(WorkWithFile.SUPPLY)) {
                supp += Integer.parseInt(index.replaceAll("\\D+", ""));
            }
        }
        return supp;
    }

    public int countBuys() {
        String[] arrayWithoutSpaces = noSpaces.splitSpaces();
        int buys = WorkWithFile.DEFAULT_NUMBER;
        for (String index : arrayWithoutSpaces) {
            if (index.contains(WorkWithFile.BUY)) {
                buys += Integer.parseInt(index.replaceAll("\\D+", ""));
            }
        }
        return buys;
    }
}
