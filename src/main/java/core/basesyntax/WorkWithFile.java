package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public static final String SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        MarketData marketDataPerDay = getResultFromFile(fromFileName);
        writeResultToFile(marketDataPerDay, toFileName);
    }

    private void writeResultToFile(MarketData marketDataPerDay, String toFileName) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, false))) {
            writeReportToFile(bufferedWriter,marketDataPerDay);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName, e);
        }
    }

    private void writeReportToFile(BufferedWriter bufferedWriter,
                                   MarketData marketDataPerDay) throws IOException {
        bufferedWriter.write(MarketDataField.SUPPLY.getTitle() + SEPARATOR
                + marketDataPerDay.getTotalSupplyAmount() + System.lineSeparator());
        bufferedWriter.write(MarketDataField.BUY.getTitle() + SEPARATOR
                + marketDataPerDay.getTotalBuyAmount() + System.lineSeparator());
        bufferedWriter.write(MarketDataField.RESULT.getTitle() + SEPARATOR
                + marketDataPerDay.calculateResult());
    }

    private MarketData getResultFromFile(String fromFileName) {
        MarketData marketData = new MarketData();
        File file = new File(fromFileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            readDataFromFile(bufferedReader,marketData);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
        return marketData;
    }

    private void readDataFromFile(BufferedReader bufferedReader,
                                  MarketData marketData) throws IOException {
        String dataLine = bufferedReader.readLine();
        while (dataLine != null) {
            String[] data = dataLine.split(SEPARATOR);
            if (data[0].equals(MarketDataField.SUPPLY.getTitle())) {
                marketData.addSupplyAmount(Integer.parseInt(data[1]));
            } else {
                marketData.addBuyAmount(Integer.parseInt(data[1]));
            }
            dataLine = bufferedReader.readLine();
        }
    }
}
