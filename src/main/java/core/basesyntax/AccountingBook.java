package core.basesyntax;

public class AccountingBook {
    private static AccountingBook instance;
    private int buy = 0;
    private int supply = 0;

    private AccountingBook() {
    }

    public static AccountingBook getInstance() {
        if (instance == null) {
            instance = new AccountingBook();
        }
        return instance;
    }

    public void addBuy(int amount) {
        buy += amount;
    }

    public void addSupply(int amount) {
        supply += amount;
    }

    public int getBuy() {
        return buy;
    }

    public int getSupply() {
        return supply;
    }

    public void erase() {
        buy = 0;
        supply = 0;
    }
}
