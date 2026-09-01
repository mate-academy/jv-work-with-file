package core.basesyntax;

public class Summary {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    private final int supply;
    private final int buy;

    public Summary(int supply, int buy) {
        this.supply = supply;
        this.buy = buy;
    }

    public int result() {
        return supply - buy;
    }

    public String toCsv() {
        return SUPPLY + "," + supply + System.lineSeparator()
                + BUY + "," + buy + System.lineSeparator()
                + RESULT + "," + result();
    }
}
