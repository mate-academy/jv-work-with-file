package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String specifiedCharacter = ",";

    public void getStatistic(String fromFileName, String toFileName) {

        File fileFrom = new File(fromFileName);

        Report reportObject = processFile(fileFrom);

        String report = createReport(
                reportObject.getTotalSupplyAmount(), reportObject.getTotalBuyAmount());

        writeToFile(toFileName, report);

    }

    private Report processFile(File fileFrom) {
        int totalSupply = 0;
        int totalBuy = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileFrom))) {

            String dataLine = bufferedReader.readLine();
            String[] separatorLine;

            while (dataLine != null) {

                separatorLine = dataLine.split(specifiedCharacter);

                totalSupply = handleOperation(totalSupply, separatorLine, "supply");

                totalBuy = handleOperation(totalBuy, separatorLine, "buy");

                dataLine = bufferedReader.readLine();

            }

        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file", e);
        }

        return new Report(totalBuy, totalSupply);

    }

    private int handleOperation(int amount, String[] operationItem, String operation) {

        if (operation.equals(operationItem[0])) {
            amount += Integer.parseInt(operationItem[1]);
        }

        return amount;
    }

    private String createReport(int supply, int buy) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supply,").append(supply).append(System.lineSeparator());
        stringBuilder.append("buy,").append(buy).append(System.lineSeparator());
        stringBuilder.append("result,").append(supply - buy).append(System.lineSeparator());

        return stringBuilder.toString();

    }

    private void writeToFile(String toFileName, String report) {

        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter(new File(toFileName)))) {

            bufferedWriter.write(report);

        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }

    }

    private static final class Report {

        private final int totalBuyAmount;
        private final int totalSupplyAmount;

        public Report(int totalBuyAmount, int totalSupplyAmount) {
            this.totalBuyAmount = totalBuyAmount;
            this.totalSupplyAmount = totalSupplyAmount;
        }

        public int getTotalBuyAmount() {
            return totalBuyAmount;
        }

        public int getTotalSupplyAmount() {
            return totalSupplyAmount;
        }

    }
}
