package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataRows;
        try {
            dataRows = getDataRows(fromFileName);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file",e);
        }
        int supply = countSupply(dataRows);
        int buy = countBuy(dataRows);
        int result = countResult(supply, buy);
        fillFile(toFileName, supply, buy, result);
    }

    private String[] getDataRows(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        int value = reader.read();
        StringBuilder stringBuilder = new StringBuilder();
        while (value != -1) {
            stringBuilder.append((char) value);
            value = reader.read();
        }
        String date = stringBuilder.toString();
        return date.split(System.lineSeparator());
    }

    private int countSupply(String[] dataRows) {
        StringBuilder supplyString = new StringBuilder();
        for (String dataRow : dataRows) {
            if (dataRow.contains("supply")) {
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
            if (dataRow.contains("buy")) {
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

    private void fillFile(String toFileName, int supply, int buy, int result) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            String content = "supply," + supply + System.lineSeparator()
                    + "buy," + buy + System.lineSeparator()
                    + "result," + result;
            bufferedWriter.write(content);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
