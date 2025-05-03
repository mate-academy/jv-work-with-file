package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String statistic = processMarketData(getMarketDataFromFile(fromFileName));
        writeMarketStatisticToFile(toFileName,statistic);
    }

    private int[] getMarketDataFromFile(String fileWithData) {
        int[] amountsSupplyAndBuy = {0,0};
        try (BufferedReader readerFromData = new BufferedReader(new FileReader(fileWithData))) {
            String lineWithOperation = readerFromData.readLine();
            while (lineWithOperation != null) {
                getAmount(lineWithOperation, amountsSupplyAndBuy);
                lineWithOperation = readerFromData.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File with data wasn't found", e);
        } catch (IOException e) {
            throw new RuntimeException("Error of reading", e);
        }
        return amountsSupplyAndBuy;
    }

    private void getAmount(String operationLine, int[] currentAmountsSupplyAndBuy) {
        int amountOnOperationLine = Integer.parseInt(operationLine
                .substring(operationLine.indexOf(",") + 1));
        switch (operationLine.substring(0, operationLine.indexOf(","))) {
            case "supply": {
                currentAmountsSupplyAndBuy[0] += amountOnOperationLine;
                break;
            }
            case "buy": {
                currentAmountsSupplyAndBuy[1] += amountOnOperationLine;
                break;
            }
            default: {
                throw new RuntimeException("Bad line in data");
            }
        }
    }

    private void writeMarketStatisticToFile(String toFileName, String processedData) {
        try (BufferedWriter writerProcessedData = new BufferedWriter(
                new FileWriter(toFileName))) {
            writerProcessedData.append(processedData);
        } catch (IOException e) {
            throw new RuntimeException("Writing error", e);
        }
    }

    private String processMarketData(int[] supplyAndBuy) {

        return "supply," + supplyAndBuy[0]
                + System.lineSeparator()
                + "buy," + supplyAndBuy[1]
                + System.lineSeparator()
                + "result," + (supplyAndBuy[0] - supplyAndBuy[1]);
    }
}
