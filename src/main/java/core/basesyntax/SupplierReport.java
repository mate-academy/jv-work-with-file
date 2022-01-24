package core.basesyntax;

public class SupplierReport {
    public String creatReport(String data) {
        final String comaSeparator = ",";
        final String spaceSeparator = " ";
        final String supply = "supply";
        final String buy = "buy";
        final String result = "result";
        final int inexOfAmount = 1;
        int supplyAmount = 0;
        int buyAmount = 0;
        String[] readiedData = (data).split(spaceSeparator);
        StringBuilder toWrite = new StringBuilder();
        for (String s : readiedData) {
            if (s.contains(supply)) {
                supplyAmount += Integer.parseInt(s.split(comaSeparator)[inexOfAmount]);
            } else {
                buyAmount += Integer.parseInt(s.split(comaSeparator)[inexOfAmount]);
            }
        }
        toWrite.append(supply).append(comaSeparator);
        toWrite.append(supplyAmount).append(System.lineSeparator());
        toWrite.append(buy).append(comaSeparator).append(buyAmount).append(System.lineSeparator());
        toWrite.append(result).append(comaSeparator).append(supplyAmount - buyAmount);
        return toWrite.toString();
    }
}
