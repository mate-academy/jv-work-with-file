package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class WorkWithFile {
    private static final String SPECIFIED_CHARACTER = ",";
    private static final String SUPPLY_OPERATION_NAME = "supply";
    private static final String BUY_OPERATION_NAME = "buy";
    private static final String REPORT_MESSAGE_FORMAT = "supply,%d%sbuy,%d%sresult,%d";
    private static final int OPERATION_NAME_INDEX = 0;
    private static final int OPERATION_AMOUNT_INDEX = 1;
    private static final int SUPPLY_TOTAL_AMOUNT_INDEX = 0;
    private static final int BUY_TOTAL_AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        File fileFrom = new File(fromFileName);
        List<String> dataFromFile = readFromFile(fileFrom);
        int[] reportArray = processDataFile(dataFromFile);
        String report = createReport(
                reportArray[SUPPLY_TOTAL_AMOUNT_INDEX], reportArray[BUY_TOTAL_AMOUNT_INDEX]);
        writeToFile(toFileName, report);
    }

    private List<String> readFromFile(File fileFrom) {
        List<String> dataFromFile = new LinkedList<String>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileFrom))) {
            String dataLine = bufferedReader.readLine();
            while (dataLine != null) {
                dataFromFile.add(dataLine);
                dataLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file: " + fileFrom, e);
        }
        return dataFromFile;
    }

    private int[] processDataFile(List<String> dataFromFile) {
        int totalSupply = 0;
        int totalBuy = 0;
        String[] separatorLine;
        for (String dataLine : dataFromFile) {
            separatorLine = dataLine.split(SPECIFIED_CHARACTER);
            totalSupply = handleOperation(totalSupply, separatorLine, SUPPLY_OPERATION_NAME);
            totalBuy = handleOperation(totalBuy, separatorLine, BUY_OPERATION_NAME);
        }
        return new int[]{ totalBuy, totalSupply};
    }

    private int handleOperation(int amount, String[] operationItem, String operation) {
        if (operation.equals(operationItem[OPERATION_NAME_INDEX])) {
            amount += Integer.parseInt(operationItem[OPERATION_AMOUNT_INDEX]);
        }
        return amount;
    }

    private String createReport(int supply, int buy) {
        return String.format(REPORT_MESSAGE_FORMAT,
                buy, System.lineSeparator(),
                supply, System.lineSeparator(),
                buy - supply);
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter(new File(toFileName)))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: " + toFileName, e);
        }
    }
}
