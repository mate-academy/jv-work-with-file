package core.basesyntax;

import java.util.ArrayList;

public class ProductsMap {
    private final ArrayList<Pair> pairs;

    public ProductsMap() {
        pairs = new ArrayList<>();
    }

    public ProductsMap(ArrayList<Pair> pairs) {
        this.pairs = new ArrayList<>(pairs);
    }

    public void addProduct(String name, int value) {
        for (Pair pair : pairs) {
            if (pair.getName().equals(name)) {
                pair.increaseQuantity(value);
                return;
            }
        }

        Pair pair = new Pair(name, value);
        pairs.add(pair);
    }

    public Integer getQuantity(String name) {
        for (Pair pair : pairs) {
            if (pair.getName().equals(name)) {
                return pair.getQuantity();
            }
        }
        return null;
    }

    public boolean isName(String name) {
        for (Pair pair : pairs) {
            if (pair.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getProducts() {
        ArrayList<String> products = new ArrayList<>();
        for (Pair pair : pairs) {
            products.add(pair.getName());
        }
        return products;
    }

    public void clearProducts() {
        pairs.clear();
    }

    @Override
    public String toString() {
        return pairs + "";
    }
}
