package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String[] TYPE_OPERATION = {"supply", "buy"};
    private static final int POSITION_OPERATION_IN_LINE = 0;
    private static final int POSITION_COST_IN_LINE = 1;
    private static final int POSITION_ALL_SUPPLY_OPERATION = 0;
    private static final int POSITION_All_BUY_OPERATION = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int[] resultOperation = new int[TYPE_OPERATION.length];
        int count = 0;

        for (String lineOperation : TYPE_OPERATION) {
            resultOperation[count] = getReadAndSumOperation(fromFileName, lineOperation);
            writeToFile(lineOperation, toFileName, resultOperation[count]);
            count++;
        }

        writeToFile("result", toFileName, resultOperation[POSITION_ALL_SUPPLY_OPERATION]
                    - resultOperation[POSITION_All_BUY_OPERATION]);
    }

    private void writeToFile(String lineOperation, String toFileName, int resultSum) {
        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter(toFileName, true))) {
            StringBuilder dataLineToFile = new StringBuilder();
            bufferedWriter.write(dataLineToFile.append(lineOperation)
                    .append(",")
                    .append(resultSum)
                    .append(System.lineSeparator())
                    .toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data file " + toFileName, e);
        }
    }

    private int getReadAndSumOperation(String fromFileName, String lineOperation) {
        int sumOperation = 0;

        try (BufferedReader bufferedReader =
                     new BufferedReader(new FileReader(new File(fromFileName)))) {
            String line = bufferedReader.readLine();

            while (line != null) {
                String[] dataLineSplit = line.split(",");

                if ((dataLineSplit[POSITION_OPERATION_IN_LINE]).equals(lineOperation)) {
                    sumOperation += Integer.parseInt(dataLineSplit[POSITION_COST_IN_LINE]);
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data file " + fromFileName, e);
        }
        return sumOperation;
    }
}
