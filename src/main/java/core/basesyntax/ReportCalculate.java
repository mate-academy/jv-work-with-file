package core.basesyntax;

public class ReportCalculate {
    private static final String SUPPLY = "supply";
    private static final String TO_BUY = "buy";
    private static final String RESULT = "result";
    private static final String DELIMITER = ",";
    private static final String SPACE = " ";

    public String calculateReportFrom(String data) {
        int toBuy = 0;
        int toSupply = 0;
        for (int i = 0; i < data.split(SPACE).length; i++) {
            if (data.split(SPACE)[i].split(DELIMITER)[0].equals("buy")) {
                toBuy += Integer.parseInt(data.split(SPACE)[i].split(DELIMITER)[1]);
            } else {
                toSupply += Integer.parseInt(data.split(SPACE)[i].split(DELIMITER)[1]);
            }
        }
        return resultData(toBuy,toSupply);
    }

    private String resultData(int totalBuy, int totalSupply) {
        StringBuilder stringBuilder = new StringBuilder(SUPPLY)
                .append(DELIMITER)
                .append(totalSupply)
                .append(System.lineSeparator())
                .append(TO_BUY)
                .append(DELIMITER)
                .append(totalBuy)
                .append(System.lineSeparator())
                .append(RESULT)
                .append(DELIMITER)
                .append(totalSupply - totalBuy);
        return stringBuilder.toString();
    }
}
