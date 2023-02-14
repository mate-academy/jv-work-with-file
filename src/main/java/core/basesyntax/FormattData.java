package core.basesyntax;

import static java.lang.Integer.parseInt;

public class FormattData extends WorkWithFile {
    public static String getFormattedData(String data) {
        int result;
        int totalSupply = 0;
        int totalBuy = 0;
        StringBuilder builder = new StringBuilder();
        String[] arr = data.split(REGEX);
        for (String tips : arr) {
            final int intValue = parseInt(tips.substring(tips.indexOf(",") + 1));
            if (tips.contains("buy")) {
                totalBuy += intValue;
            } else if (tips.contains("supply")) {
                totalSupply += intValue;
            }
        }
        result = totalSupply - totalBuy;
        builder.append(SUPPLY).append(RESULT_REGEX)
                .append(totalSupply).append(System.lineSeparator())
                .append(BUY).append(RESULT_REGEX).append(totalBuy)
                .append(System.lineSeparator())
                .append(RESULT_NAME).append(RESULT_REGEX).append(result);
        return builder.toString();
    }
}
