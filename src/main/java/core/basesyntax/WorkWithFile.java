package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String SUPPLY_OPERATION_TYPE = OperationType.SUPPLY.name().toLowerCase();
    private static final String BUY_OPERATION_TYPE = OperationType.BUY.name().toLowerCase();
    private static StringBuilder result;

    public void getStatistic(String fromFileName, String toFileName) {
        getReportFromFile(fromFileName);
        writeReportToFile(toFileName);
    }

    private void getReportFromFile(String fromFileName) {
        File fromFile = new File(fromFileName);
        int firstOperationTypeSum = 0;
        int secondOperationTypeSum = 0;
        String dataRow;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile));
            dataRow = bufferedReader.readLine();
            do {
                String[] dataRowSplit = dataRow.split(",");
                if (SUPPLY_OPERATION_TYPE.equals(dataRowSplit[OPERATION_TYPE_INDEX])) {
                    firstOperationTypeSum += Integer.valueOf(dataRowSplit[AMOUNT_INDEX]);
                } else if (BUY_OPERATION_TYPE.equals(dataRowSplit[OPERATION_TYPE_INDEX])) {
                    secondOperationTypeSum += Integer.valueOf(dataRowSplit[AMOUNT_INDEX]);
                }
                dataRow = bufferedReader.readLine();
            } while (dataRow != null);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }

        result = new StringBuilder().append(SUPPLY_OPERATION_TYPE).append(',')
                .append(firstOperationTypeSum)
                .append(System.lineSeparator())
                .append(BUY_OPERATION_TYPE).append(',').append(secondOperationTypeSum)
                .append(System.lineSeparator())
                .append("result,").append(firstOperationTypeSum - secondOperationTypeSum);
    }

    private void writeReportToFile(String toFileName) {
        File toFile = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            bufferedWriter.write(result.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
