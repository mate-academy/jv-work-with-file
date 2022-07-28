package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final int OPERATION = 0;
    private static final int AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int totalSupply = 0;
        int totalBuy = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                String[] valueArray = value.split(",");
                String operation = valueArray[OPERATION];
                int amount = Integer.parseInt(valueArray[AMOUNT]);
                totalSupply = operation.equals(SUPPLY) ? totalSupply + amount : totalSupply;
                totalBuy = operation.equals(BUY) ? totalBuy + amount : totalBuy;
                value = bufferedReader.readLine();
            }
            bufferedWriter.write(getResult(totalSupply, totalBuy));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String getResult(int totalSupply, int totalBuy) {
        int result = totalSupply - totalBuy;
        return SUPPLY + "," + totalSupply + System.lineSeparator() + BUY
                + "," + totalBuy + System.lineSeparator()
                + "result," + result;
    }
}
