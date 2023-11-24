package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final int ACTION_INDEX = 0;
    private static final int MOUNT_INDEX = 1;
    public void getStatistic(String fromFileName, String toFileName) {
        File fileToRead = new File(fromFileName);
        File fileToWrite = new File(toFileName);
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuilderBuy = new StringBuilder();
        StringBuilder stringBuilderSupply = new StringBuilder();
        StringBuilder stringBuilderResult = new StringBuilder();
        int buyScore = 0;
        int supplyScore = 0;
        int countBuyLength = 0;
        int countSupplyLength = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileToRead))) {
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(',');
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot read file", e);
        }
        String result = stringBuilder.toString();//buy,13,sell,2....
        String[] allStrings = result.split(",");//[buy, 13, sell, 2]
        for (int i = 0; i < allStrings.length; i = i + 2) {//[0]buy
            if(allStrings[i].equals(BUY)) {
                buyScore += Integer.parseInt(allStrings[i + 1]);
                stringBuilderBuy.append(allStrings[i + 1]).append(',');
                countBuyLength++;
            }
            if(allStrings[i].equals(SUPPLY)) {//+
                supplyScore += Integer.parseInt(allStrings[i + 1]);
                stringBuilderSupply.append(allStrings[i + 1]).append(",");
                countSupplyLength++;
            }
        }
        String[] buyWithComma = stringBuilderBuy.toString().split(",");
        String[] supplyWithComma = stringBuilderSupply.toString().split(",");

        String[] buy = new String[countBuyLength];
        String[] supply  = new String[countSupplyLength];
        int counterOfBuy = 0;
        int counterOfSupply = 0;
        for (String current : buyWithComma) {
            if (!current.equals(",")) {
                buy[counterOfBuy] = current;
                counterOfBuy++;
            }
        }
        for (String current : supplyWithComma) {
            if (!current.equals(",")) {
                supply[counterOfSupply] = current;
                counterOfSupply++;
            }
        }
        stringBuilderBuy.setLength(0);
        stringBuilderBuy.append("buy = ");
        stringBuilderBuy.setLength(0);
        for (int i = 0; i < buy.length; i++) {
            stringBuilderBuy.append(buy[i]);
            if(i != buy.length - 1) {
                stringBuilderBuy.append(" + ");
            }
            if (i == buy.length - 1) {
                stringBuilderBuy.append(" = ").append(buyScore);
            }
        }
        String resultBuy = stringBuilderBuy.toString();
        stringBuilderSupply.setLength(0);
        stringBuilderSupply.append("supply = ");
        stringBuilderSupply.setLength(0);
        for (int i = 0; i < supply.length; i++) {
            stringBuilderSupply.append(supply[i]);
            if(i != supply.length - 1) {
                stringBuilderSupply.append(" + ");
            }
            if (i == supply.length - 1) {
                stringBuilderSupply.append(" = ").append(supplyScore);
            }
        }
        String resultSupply = stringBuilderSupply.toString();
        String resultFinal = stringBuilderResult.append("result = supply - buy = ")
                .append(supplyScore).append(" - ").append(buyScore).append(" = ")
                .append(supplyScore - buyScore).toString();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToWrite))) {
            writer.write(resultSupply + System.lineSeparator());
            writer.write(resultBuy + System.lineSeparator());
            writer.write(resultFinal + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Cannot write in file",e);
        }
    }
}
