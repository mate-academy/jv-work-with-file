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

    public void getStatistic(String fromFileName, String toFileName) {
        String textFromFile = readFromFile(fromFileName);
        String report = createReport(textFromFile);
        writeFile(toFileName, report);
    }

    private String readFromFile(String fromFileName) {
        File fromFile = new File(fromFileName);
        String dataRow;
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile))) {
            dataRow = bufferedReader.readLine();
            do {
                stringBuilder.append(dataRow).append(System.lineSeparator());
                dataRow = bufferedReader.readLine();
            } while (dataRow != null);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
        return stringBuilder.toString();
    }

    private String createReport(String textFromFile) {
        int firstOperationTypeSum = 0;
        int secondOperationTypeSum = 0;
        String[] dataRowSplit = textFromFile.split(System.lineSeparator());
        for (String dataRow : dataRowSplit) {
            String[] dataRowAndColumnSplit = dataRow.split(",");
            if (SUPPLY_OPERATION_TYPE.equals(dataRowAndColumnSplit[OPERATION_TYPE_INDEX])) {
                firstOperationTypeSum += Integer.valueOf(dataRowAndColumnSplit[AMOUNT_INDEX]);
            } else if (BUY_OPERATION_TYPE.equals(dataRowAndColumnSplit[OPERATION_TYPE_INDEX])) {
                secondOperationTypeSum += Integer.valueOf(dataRowAndColumnSplit[AMOUNT_INDEX]);
            }
        }
        return new StringBuilder().append(SUPPLY_OPERATION_TYPE).append(',')
                .append(firstOperationTypeSum)
                .append(System.lineSeparator())
                .append(BUY_OPERATION_TYPE).append(',').append(secondOperationTypeSum)
                .append(System.lineSeparator())
                .append("result,").append(firstOperationTypeSum - secondOperationTypeSum)
                .toString();
    }

    private void writeFile(String toFileName, String report) {
        File toFile = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }
}
