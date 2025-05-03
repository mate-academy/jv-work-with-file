package core.basesyntax;

public class ReportGenerator {
    public String reportGenerator(int result, int supplyCount, int buyCount) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supply,").append(supplyCount).append(System.lineSeparator());
        stringBuilder.append("buy,").append(buyCount).append(System.lineSeparator());
        stringBuilder.append("result,").append(result);
        return stringBuilder.toString();
    }
}
