package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMMA_SEPARATOR = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private int supplySum;
    private int buySum;

    public void getStatistic(String fromFileName, String toFileName) {
        readFromFileAndSum(fromFileName);
        writeToFile(toFileName, createReport());
    }

    private void readFromFileAndSum(String fname) {
        File file = new File(fname);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String data = bufferedReader.readLine();
            while (data != null) {
                sumAmountByOperation(data);
                data = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file.", e);
        }
    }

    private void sumAmountByOperation(String data) {
        String[] splittedData = data.split(COMMA_SEPARATOR);
        switch (splittedData[OPERATION_TYPE_INDEX]) {
            case SUPPLY:
                supplySum += Integer.parseInt(splittedData[AMOUNT_INDEX]);
                break;
            case BUY:
                buySum += Integer.parseInt(splittedData[AMOUNT_INDEX]);
                break;
            default:
        }
    }

    private int getResult() {
        return supplySum - buySum;
    }

    private String createReport() {
        StringBuilder resultReport = new StringBuilder();
        resultReport.append(SUPPLY).append(COMMA_SEPARATOR).append(supplySum)
                .append(System.lineSeparator())
                .append(BUY).append(COMMA_SEPARATOR).append(buySum)
                .append(System.lineSeparator())
                .append("result").append(COMMA_SEPARATOR).append(getResult());
        return resultReport.toString();
    }

    private void writeToFile(String fname, String data) {
        File myFile = new File(fname);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(myFile))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write the file.", e);
        }
    }
}
