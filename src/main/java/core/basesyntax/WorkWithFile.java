package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final int TOTAL_SUPPLY_INDEX = 0;
    private static final int TOTAL_BUY_INDEX = 1;
    private static final int SUPPLY_OR_BUY_INDEX = 0;
    private static final int VALUE_OF_SUPPLY_OR_BUY_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int totalSupply = 0;
        int totalBuy = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] refactored = line.split(",");
                totalSupply += getTotalSupAndBuy(refactored)[TOTAL_SUPPLY_INDEX];
                totalBuy += getTotalSupAndBuy(refactored)[TOTAL_BUY_INDEX];
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read this file" + fromFileName, e);
        }
        writeToFile(toFileName, totalSupply, totalBuy);
    }

    public int[] getTotalSupAndBuy(String[] refactored) {
        int totalSupply = 0;
        int totalBuy = 0;
        switch (refactored[SUPPLY_OR_BUY_INDEX]) {
            case SUPPLY:
                totalSupply = Integer.parseInt(refactored[VALUE_OF_SUPPLY_OR_BUY_INDEX]);
                break;
            case BUY:
                totalBuy = Integer.parseInt(refactored[VALUE_OF_SUPPLY_OR_BUY_INDEX]);
                break;
            default:
                break;
        }
        return new int[] {totalSupply, totalBuy};
    }

    private void writeToFile(String toFileName, int totalSup, int totalBuy) {
        StringBuilder reportAboutDay = new StringBuilder();
        int result = totalSup - totalBuy;
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            reportAboutDay
                    .append(SUPPLY).append(",").append(totalSup).append(System.lineSeparator())
                    .append(BUY).append(",").append(totalBuy).append(System.lineSeparator())
                    .append("result,").append(result);
            bufferedWriter.write(reportAboutDay.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write into file" + toFileName, e);
        }
    }
}
