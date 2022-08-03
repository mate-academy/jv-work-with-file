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
        final String SEPARATOR = ";";
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(SEPARATOR);
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + file, e);
        }
        return builder.toString();
    }

    private MarketStatistic getMarketStatistic(String content) {
        String[] listOfStatistic = content.split(";");
        int supplyCount = 0;
        int buyCount = 0;
        for (String statistic : listOfStatistic) {
            int statisticNumber = Integer.parseInt(statistic.split(",")[1]);
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
            bufferedWriter.write(new StringBuilder().append("supply,").append(supplyCount)
                    .append(System.lineSeparator()).append("buy,").append(buyCount)
                    .append(System.lineSeparator()).append("result,").append(result).toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + file, e);
        }
    }
}
