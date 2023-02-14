package core.basesyntax;

public enum MarketDataField {
    SUPPLY("supply"),
    BUY("buy"),
    RESULT("result");

    private final String title;

    MarketDataField(String title) {
        this.title = title;
    }

    String getTitle() {
        return title;
    }
}
