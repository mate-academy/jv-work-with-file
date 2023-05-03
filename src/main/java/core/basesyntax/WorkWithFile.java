package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_OPERATION = "supply";
    private static final String BUY_OPERATION = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataRows= getDataRows(fromFileName);
        int supply = countSupply(dataRows);
        int buy = countBuy(dataRows);
        int result = countResult(supply, buy);
        String report = getAmountToString(supply, buy, result);
        fillFile(toFileName, report);
    }

    private String[] getDataRows(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            int value = reader.read();
            StringBuilder dataRows = new StringBuilder();
            while (value != -1) {
                dataRows.append((char) value);
                value = reader.read();
            }
            String date = dataRows.toString();
            return date.split(System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Cannot read the file " + fileName, e);
        }
    }

    private int countSupply(String[] dataRows) {
        StringBuilder supplyString = new StringBuilder();
        for (String dataRow : dataRows) {
            if (dataRow.contains(SUPPLY_OPERATION)) {
                supplyString.append(dataRow).append(",");
            }
        }
        String[] supplyArray = supplyString.toString().split(",");
        int supply = 0;
        for (int i = 1; i < supplyArray.length; i = i + 2) {
            supply = supply + Integer.parseInt(supplyArray[i]);
        }
        return supply;
    }

    private int countBuy(String[] dataRows) {
        StringBuilder buyString = new StringBuilder();
        for (String dataRow : dataRows) {
            if (dataRow.contains(BUY_OPERATION)) {
                buyString.append(dataRow).append(",");
            }
        }
        String[] buyArray = buyString.toString().split(",");
        int buy = 0;
        for (int i = 1; i < buyArray.length; i = i + 2) {
            buy = buy + Integer.parseInt(buyArray[i]);
        }
        return buy;
    }

    private int countResult(int supply, int buy) {
        return supply - buy;
    }

    private String getAmountToString(int supply, int buy, int result) {
        StringBuilder builder = new StringBuilder();
        builder.append("supply,").append(supply).append(System.lineSeparator());
        builder.append("buy,").append(buy).append(System.lineSeparator());
        builder.append("result,").append(result).append(System.lineSeparator());
        return builder.toString();
    }

    private void fillFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName, e);
        }
    }
}
