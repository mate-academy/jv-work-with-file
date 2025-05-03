package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SPLITTER_FIELD = ",";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String OPERATION_SUPPLY = "supply";
    private static final String OPERATION_BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] linesFromFile = readFromFile(fromFileName);
        String report = getReport(linesFromFile);
        writeToFile(report, toFileName);
    }

    private String[] readFromFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
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

    private String getReport(String[] dataLinesFromFile) {
        StringBuilder resultReport = new StringBuilder();
        int totalSupplyAmount = 0;
        int totalBuyAmount = 0;
        for (String dataLine : dataLinesFromFile) {
            String[] dataField = dataLine.split(SPLITTER_FIELD);
            String operationType = dataField[OPERATION_TYPE_INDEX];
            int amount = Integer.parseInt(dataField[AMOUNT_INDEX]);
            switch (operationType) {
                case OPERATION_SUPPLY:
                    totalSupplyAmount += amount;
                    break;
                case OPERATION_BUY:
                    totalBuyAmount += amount;
                    break;
                default:
                    throw new RuntimeException("Data is incorrect");
            }
        }
        return resultReport.append(OPERATION_SUPPLY).append(SPLITTER_FIELD)
                .append(totalSupplyAmount).append(System.lineSeparator()).append(OPERATION_BUY)
                .append(SPLITTER_FIELD).append(totalBuyAmount).append(System.lineSeparator())
                .append("result,").append(totalSupplyAmount - totalBuyAmount).toString();
    }

    private void writeToFile(String resultReport, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(resultReport);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file:" + fileName, e);
        }
    }
}
