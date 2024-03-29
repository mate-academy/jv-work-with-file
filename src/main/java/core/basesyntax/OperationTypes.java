package core.basesyntax;

public enum OperationTypes {
    SUPPLY {
        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }
    },
    BUY {
        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }
    }
}
