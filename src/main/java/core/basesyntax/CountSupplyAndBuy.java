package core.basesyntax;

public class CountSupplyAndBuy {
    private final String replaceChar = "";
    private final int initialAmount = 0;

    public int countSupply(String[] array) {
        int countingSupply = initialAmount;
        for (String index : array) {
            if (index.contains(WorkWithFile.SUPPLY)) {
                countingSupply += Integer.parseInt(index.replaceAll("\\D+", replaceChar));
            }
        }
        return countingSupply;
    }

    public int countBuy(String[] array) {
        int countingBuy = initialAmount;
        for (String index : array) {
            if (index.contains(WorkWithFile.BUY)) {
                countingBuy += Integer.parseInt(index.replaceAll("\\D+", replaceChar));
            }
        }
        return countingBuy;
    }
}
