package core.basesyntax;

public class EntitySupplyBuy {
    private int buy;
    private int supply;
    private int result = 0;

    public EntitySupplyBuy(int buy, int supply) {
        this.buy = buy;
        this.supply = supply;
    }

    public int getBuy() {
        return this.buy;
    }

    public int getSupply() {
        return this.supply;
    }

    public int getResult() {
        return this.result;
    }

    private void increaseBuy(int value) {
        this.buy += value;
        this.updateResult();
    }

    private void increaseSupply(int value) {
        this.supply += value;
        this.updateResult();
    }

    private void updateResult() {
        this.result = this.getSupply() - this.getBuy();
    }

    public void changeEntityState(String field, int value) {
        switch (field) {
            case "buy":
                increaseBuy(value);
                break;
            case "supply":
                increaseSupply(value);
                break;
            default:
                break;
        }
    }
}
