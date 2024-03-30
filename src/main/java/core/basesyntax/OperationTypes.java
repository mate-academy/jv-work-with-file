package core.basesyntax;

public enum OperationTypes {
    SUPPLY,
    BUY,
    RESULT;
    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
