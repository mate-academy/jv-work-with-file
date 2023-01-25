package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private boolean isInitialRead = true;

    public void getStatistic(String fromFileName, String toFileName) {
        if (isInitialRead) {
            String[] dataLinesFromFile = readFromFile(fromFileName);
            String resultReport = getReport(dataLinesFromFile);
            writeToFile(resultReport, toFileName);
        }
    }

    static String[] readFromFile(String fileName) {
        File file = new File(fileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String dataLine = reader.readLine();
            while (dataLine != null) {
                stringBuilder.append(dataLine).append(System.lineSeparator());
                dataLine = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fileName, e);
        }
        return stringBuilder.toString().split(System.lineSeparator());
    }

    static String getReport(String[] dataLinesFromFile) {
        final String splitterFields = ",";
        final int operationTypeIndex = 0;
        final int amountIndex = 1;
        final String operationSupply = "supply";
        final String operationBuy = "buy";
        StringBuilder resultReport = new StringBuilder();
        int totalSupplyAmount = 0;
        int totalBuyAmount = 0;
        for (String dataLine : dataLinesFromFile) {
            String[] dataField = dataLine.split(splitterFields);
            String operationType = dataField[operationTypeIndex];
            int amount = Integer.parseInt(dataField[amountIndex]);
            switch (operationType) {
                case operationSupply:
                    totalSupplyAmount += amount;
                    break;
                case operationBuy:
                    totalBuyAmount += amount;
                    break;
                default:
                    throw new RuntimeException("Data is incorrect");
            }
        }
        return resultReport.append(operationSupply).append(splitterFields)
                .append(totalSupplyAmount).append(System.lineSeparator()).append(operationBuy)
                .append(splitterFields).append(totalBuyAmount).append(System.lineSeparator())
                .append("result,").append(totalSupplyAmount - totalBuyAmount).toString();
    }

    private void writeToFile(String resultReport, String fileName) {
        File file = new File(fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(resultReport);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        } finally {
            isInitialRead = false;
        }
    }
}
