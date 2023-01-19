package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_WORD = "supply";
    private static final String BUY_WORD = "buy";
    private static final String RESULT_WORD = "result";
    private static final int NAME_INDEX = 0;
    private static final int NUMBER_INDEX = 1;
    private static final String GET_SUM_SUPPLY = "getSupply";
    private static final String GET_SUM_BUY = "getBuy";

    public void getStatistic(String fromFileName, String toFileName) {
        getArrayFile(fromFileName);

        int sumSupply = getSum(GET_SUM_SUPPLY, fromFileName);
        int sumBuy = getSum(GET_SUM_BUY, fromFileName);
        int sumResult = sumSupply - sumBuy;
        StringBuilder builder = new StringBuilder();
        builder.append(SUPPLY_WORD).append(",").append(sumSupply).append(" ")
                .append(BUY_WORD).append(",").append(sumBuy).append(" ")
                .append(RESULT_WORD).append(",").append(sumResult);
        String[] builderArr = builder.toString().split(" ");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            for (String value : builderArr) {
                writer.write(value);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + fromFileName, e);
        }
    }

    private String[] getArrayFile(String fileName) {
        StringBuilder builder = new StringBuilder();
        File file = new File(fileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(" ");
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + file, e);
        }
        String[] stringFileArr = builder.toString().split(" ");
        return stringFileArr;
    }

    private int getSum(String getSum, String fileName) {
        String[] stringFileArr = getArrayFile(fileName);
        int sumSupply = 0;
        int sumBuy = 0;

        for (int i = 0; i < stringFileArr.length; i++) {
            String[] stringValue = stringFileArr[i].split(",");
            String name = stringValue[NAME_INDEX];
            sumSupply += name.equals(SUPPLY_WORD) ? Integer.parseInt(stringValue[NUMBER_INDEX]) : 0;
            sumBuy += name.equals(BUY_WORD) ? Integer.parseInt(stringValue[NUMBER_INDEX]) : 0;
        }
        if (getSum.equals(GET_SUM_SUPPLY)) {
            return sumSupply;
        }
        return sumBuy;
    }
}
