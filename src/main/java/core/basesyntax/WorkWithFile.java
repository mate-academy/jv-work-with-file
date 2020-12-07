package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String REGEX = ",";
    private static final String SUPPLY_OPERATION = "supply";
    private static final String BUY_OPERATION = "buy";
    private static final int OPERATION_TYPE = 0;
    private static final int AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        File toFile = new File(toFileName);
        writeReport(sumOfOperation(SUPPLY_OPERATION, fromFile),
                    sumOfOperation(BUY_OPERATION, fromFile),
                    toFile);
    }

    private int sumOfOperation(String operationName, File fromFileName) {
        int sum = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String readValue = bufferedReader.readLine();
            while (readValue != null) {
                if (readValue.split(REGEX)[OPERATION_TYPE].equals(operationName)) {
                    sum += Integer.parseInt(readValue.split(REGEX)[AMOUNT]);
                }
                readValue = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }
        return sum;
    }

    private void writeReport(int supply, int buy, File toFile) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            bufferedWriter.write(SUPPLY_OPERATION);
            bufferedWriter.write(REGEX);
            bufferedWriter.write(String.valueOf(supply));
            bufferedWriter.newLine();

            bufferedWriter.write(BUY_OPERATION);
            bufferedWriter.write(REGEX);
            bufferedWriter.write(String.valueOf(buy));
            bufferedWriter.newLine();

            bufferedWriter.write("result");
            bufferedWriter.write(REGEX);
            bufferedWriter.write(String.valueOf(supply - buy));

            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file", e);
        }
    }
}
