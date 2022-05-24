package core.basesyntax;

public class TaskFinalizer {
    private static final String RESULT_STRING = "result";
    private static final char COMMA_SEPARATOR = ',';
    public String finalString(String[] data) {
        StringBuilder builder = new StringBuilder();
        AmountCounter buy = new AmountCounter();
        AmountCounter supply = new AmountCounter();
        String supplyFinal = supply.count(data, "supply");
        String buyFinal = buy.count(data, "buy");
        return builder.append(supplyFinal)
                .append(System.lineSeparator())
                .append(buyFinal)
                .append(System.lineSeparator())
                .append(RESULT_STRING)
                .append(COMMA_SEPARATOR)
                .append(supply.getCounter() - buy.getCounter())
                .toString();
    }
}
