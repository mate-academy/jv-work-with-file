package core.basesyntax;

public class Pair {
    private String name;
    private int quantity;

    public Pair(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void increaseQuantity(int value) {
        this.quantity += value;
    }

    @Override
    public String toString() {
        return name + ','
                + quantity;
    }
}
