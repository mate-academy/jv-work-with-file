package core.basesyntax;

public class StatisticCreator {
    private static final int PROCESS_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final ReportGenerator reportGenerator = new ReportGenerator();

    public String statisticCreator(String info) {
        int supplyCount = 0;
        int buyCount = 0;

        String[] lines = info.trim().split("\\R");
        for (String line: lines) {
            String[] amount = line.split(",");
            String process = amount[PROCESS_TYPE_INDEX].trim();
            int value = Integer.parseInt(amount[AMOUNT_INDEX].trim());

            if (process.equals("supply")) {
                supplyCount += value;
            } else if (process.equals("buy")) {
                buyCount += value;
            }
        }
        int result = supplyCount - buyCount;
        return reportGenerator.reportGenerator(result, supplyCount, buyCount);
    }
}
