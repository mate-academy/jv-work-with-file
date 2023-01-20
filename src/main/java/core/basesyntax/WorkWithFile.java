package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    private static final String SUPPLY_WORD = "supply";
    private static final String BUY_WORD = "buy";
    private static final String RESULT_WORD = "result";
    private static final int NAME_INDEX = 0;
    private static final int NUMBER_INDEX = 1;
    private static final String GET_SUM_SUPPLY = "getSupply";
    private static final String GET_SUM_BUY = "getBuy";

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(fromFileName, toFileName);
    }

    private String[] readFile(String fileName) {
        File file = new File(fileName);
        String[] stringFileArr;
        try {
            stringFileArr = Files.readAllLines(file.toPath()).toArray(new String[0]);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + file, e);
        }
        return stringFileArr;
    }

    private int generateReport(String getSum, String fileName) {
        String[] stringFileArr = readFile(fileName);
        int sumSupply = 0;
        int sumBuy = 0;

        for (int i = 0; i < stringFileArr.length; i++) {
            String[] stringValue = stringFileArr[i].split(",");
            sumSupply += stringValue[NAME_INDEX].equals(SUPPLY_WORD)
                    ? Integer.parseInt(stringValue[NUMBER_INDEX]) : 0;
            sumBuy += stringValue[NAME_INDEX].equals(BUY_WORD)
                    ? Integer.parseInt(stringValue[NUMBER_INDEX]) : 0;
        }
        if (getSum.equals(GET_SUM_SUPPLY)) {
            return sumSupply;
        }
        return sumBuy;
    }

    private void writeToFile(String fromFileName, String toFileName) {
        int sumSupply = generateReport(GET_SUM_SUPPLY, fromFileName);
        int sumBuy = generateReport(GET_SUM_BUY, fromFileName);
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
}
