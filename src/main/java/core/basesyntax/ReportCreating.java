package core.basesyntax;

public class ReportCreating {
    public static final String COMMA = ",";

    public String[] createReport(String[] data) {
        String supply = "supply";
        int supplyCounter = 0;
        String buy = "buy";
        int buyCounter = 0;
        for (String stringData : data) {
            String[] stringDataArray = stringData.split(COMMA);
            if (stringDataArray[0].equals(supply)) {
                supplyCounter = supplyCounter + Integer.valueOf(stringDataArray[1]);
            }
            if (stringDataArray[0].equals(buy)) {
                buyCounter = buyCounter + Integer.valueOf(stringDataArray[1]);
            }
        }
        return new String[] {(supply + COMMA + supplyCounter),
                (buy + COMMA + buyCounter),
                ("result" + COMMA + (supplyCounter - buyCounter))};
    }
}
