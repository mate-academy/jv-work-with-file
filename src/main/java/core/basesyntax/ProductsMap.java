package core.basesyntax;

import java.util.ArrayList;

public class ProductsMap {
    private final ArrayList<ProductQuantity> productQuantities;

    public ProductsMap() {
        productQuantities = new ArrayList<>();
    }

    public void addProduct(String product, int quantity) {
        for (ProductQuantity productQuantity : productQuantities) {
            if (productQuantity.getProduct().equals(product)) {
                productQuantity.increaseQuantity(quantity);
                return;
            }
        }

        ProductQuantity productQuantity = new ProductQuantity(product, quantity);
        productQuantities.add(productQuantity);
    }

    public int getQuantity(String product) {
        for (ProductQuantity productQuantity : productQuantities) {
            if (productQuantity.getProduct().equals(product)) {
                return productQuantity.getQuantity();
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return productQuantities.toString();
    }

    private class ProductQuantity {
        private String product;
        private int quantity;

        public ProductQuantity(String product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }

        public String getProduct() {
            return product;
        }

        public void setProduct(String product) {
            this.product = product;
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
            return product
                    + Constants.DELIMITER
                    + quantity;
        }
    }
}
