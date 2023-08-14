package core.basesyntax;

public class Calculate {
    private static final String SUPPLY = "supply,";
    private static final String TO_BUY = "buy,";
    private static final String RESULT = "result,";

    public String calculateBuySupplyData(String data) {
        int toBuy = 0;
        int toSupply = 0;
        for (int i = 0; i < data.split(" ").length; i++) {
            if (data.split(" ")[i].split(",")[0].equals("buy")) {
                toBuy += Integer.parseInt(data.split(" ")[i].split(",")[1]);
            } else {
                toSupply += Integer.parseInt(data.split(" ")[i].split(",")[1]);
            }
        }
        return resultData(toBuy,toSupply);
    }

    private String resultData(int totalBuy, int totalSupply) {
        StringBuilder stringBuilder = new StringBuilder(SUPPLY + totalSupply)
                .append(System.lineSeparator())
                .append(TO_BUY + totalBuy)
                .append(System.lineSeparator())
                .append(RESULT + (totalSupply - totalBuy));
        return stringBuilder.toString();
    }
}
