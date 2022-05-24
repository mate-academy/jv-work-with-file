package core.basesyntax;

public class AmountCounter {
    private int supplyCounter;
    private int buyCounter;

    public String count(String[] data) {
        for (String value : data) {
            if (value.split(",")[0].equals("supply")) {
                supplyCounter += Integer.parseInt(value.split(",")[1]);
            } else if (value.split(",")[0].equals("buy")) {
                buyCounter += Integer.parseInt(value.split(",")[1]);
            }
        }
        return new StringBuilder()
                .append("supply")
                .append(",")
                .append(supplyCounter)
                .append(System.lineSeparator())
                .append("buy")
                .append(",")
                .append(buyCounter)
                .append(System.lineSeparator())
                .append("result")
                .append(",")
                .append(supplyCounter - buyCounter)
                .toString();
    }
}
