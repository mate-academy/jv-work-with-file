package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_OPERATION = "supply";
    private static final String BUY_OPERATION = "buy";
    private static final String RESULT_OPERATION = "result";
    private static final String SEPARATOR = ",";
    private static final int AMOUNT_INDEX = 1;
    private static final int OPERATION_INDEX = 0;
    private static final int SUM_START_VALUE = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        writeInFile(getResult(readFile(fromFileName)), toFileName);
    }

    private String[] readFile(String fromFileName) {
        StringBuilder readFile = new StringBuilder();
        String oneLine;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            oneLine = bufferedReader.readLine();
            while (oneLine != null) {
                readFile.append(oneLine).append(System.lineSeparator());
                oneLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("The file at path" + fromFileName + " was not read : " + e);
        }
        return readFile.toString().split(System.lineSeparator());
    }

    private String getResult(String[] lines) {
        String[] splitLine;
        int sumSupply = SUM_START_VALUE;
        int sumBuy = SUM_START_VALUE;
        for (String oneLine: lines) {
            splitLine = oneLine.split(SEPARATOR);
            if (splitLine[OPERATION_INDEX].equals(SUPPLY_OPERATION)) {
                sumSupply += Integer.parseInt(splitLine[AMOUNT_INDEX]);
            } else if (splitLine[OPERATION_INDEX].equals(BUY_OPERATION)) {
                sumBuy += Integer.parseInt(splitLine[AMOUNT_INDEX]);
            }
        }
        return createReport(sumSupply,sumBuy);
    }

    private String createReport(int sumSupply, int sumBuy) {
        return new StringBuilder().append(SUPPLY_OPERATION).append(SEPARATOR).append(sumSupply)
                .append(System.lineSeparator())
                .append(BUY_OPERATION)
                .append(SEPARATOR).append(sumBuy)
                .append(System.lineSeparator())
                .append(RESULT_OPERATION)
                .append(SEPARATOR).append(sumSupply - sumBuy).toString();
    }

    private void writeInFile(String resultInString, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(resultInString);
        } catch (IOException e) {
            throw new RuntimeException("The file at path " + toFileName + " was not written" + e);
        }
    }
}
