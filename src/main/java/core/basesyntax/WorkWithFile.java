package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        File readFile = new File(fromFileName);
        File writeFile = new File(toFileName);

        String fileContent = readFromFile(readFile);
        MarketStatistic marketStatistic = getMarketStatistic(fileContent);
        writeToFile(marketStatistic.getBuyCount(), marketStatistic.getSupplyCount(), writeFile);
    }

    private String readFromFile(File file) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(";");
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return builder.toString();
    }

    private MarketStatistic getMarketStatistic(String content) {
        String[] listOfStatistic = content.split(";");
        int supplyCount = 0;
        int buyCount = 0;
        for (String statistic : listOfStatistic) {
            int statisticNumber = Integer.parseInt(statistic
                    .substring(statistic.indexOf(',') + 1));
            if (statistic.contains("supply")) {
                supplyCount += statisticNumber;
            } else if (statistic.contains("buy")) {
                buyCount += statisticNumber;
            }
        }
        return new MarketStatistic(buyCount, supplyCount);
    }

    private void writeToFile(int buyCount, int supplyCount, File file) {
        int result = supplyCount - buyCount;
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write("supply," + supplyCount + System.lineSeparator()
                    + "buy," + buyCount + System.lineSeparator() + "result," + result);
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
