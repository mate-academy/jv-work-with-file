package core.basesyntax;

public class ReportCreator {
    public static final int ACTION_INDEX = 0;
    public static final int COUNT_INDEX = 1;

    public String createReport(String[] data) {
        int supplyCounter = 0;
        int buyCounter = 0;
        for (String line : data) {
            String[] splittedLine = line.split(",");
            if (splittedLine[ACTION_INDEX].equals("supply")) {
                supplyCounter = supplyCounter + Integer.valueOf(splittedLine[COUNT_INDEX]);
            }
            if (splittedLine[ACTION_INDEX].equals("buy")) {
                buyCounter = buyCounter + Integer.valueOf(splittedLine[COUNT_INDEX]);
            }
        }
        return new StringBuilder("supply,").append(supplyCounter).append(System.lineSeparator())
                .append("buy,").append(buyCounter).append(System.lineSeparator())
                .append("result,").append(supplyCounter - buyCounter).toString();
    }
}
