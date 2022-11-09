package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_OPERATION_TYPE = "supply";
    private static final String BUY_OPERATION_TYPE = "buy";
    private static final String RESULT_OPERATION_TYPE = "result";
    private static final String[] OPERATION_TYPE_FIELDS =
            {SUPPLY_OPERATION_TYPE, BUY_OPERATION_TYPE, RESULT_OPERATION_TYPE};
    private static final int SUPPLY_AMOUNT_TYPE_INDEX = 0;
    private static final int BUY_AMOUNT_TYPE_INDEX = 1;
    private static final int RESULT_AMOUNT_TYPE_INDEX = 2;
    private static final int FIELD_INDEX = 0;
    private static final int VALUE_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFile(fromFileName);
        int[] amountTypeFields = new int[OPERATION_TYPE_FIELDS.length];
        calculateValuesForAmountTypeFields(amountTypeFields, dataFromFile);
        String report = createReport(amountTypeFields);
        writeFile(toFileName, report);
    }

    private String readFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String lineFromFile = bufferedReader.readLine();
            while (lineFromFile != null) {
                stringBuilder.append(lineFromFile).append(System.lineSeparator());
                lineFromFile = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fileName, e);
        }
        return stringBuilder.toString();
    }

    private void calculateValuesForAmountTypeFields(int[] amountTypeFields, String dataFromFile) {
        String[] linesFromFile = dataFromFile.split(System.lineSeparator());
        for (String lineFromFile : linesFromFile) {
            String[] dataFieldElements = lineFromFile.split(",");
            int valueOfField = Integer.parseInt(dataFieldElements[VALUE_INDEX]);
            switch (dataFieldElements[FIELD_INDEX]) {
                case SUPPLY_OPERATION_TYPE:
                    amountTypeFields[SUPPLY_AMOUNT_TYPE_INDEX] += valueOfField;
                    break;
                case BUY_OPERATION_TYPE:
                    amountTypeFields[BUY_AMOUNT_TYPE_INDEX] += valueOfField;
                    break;
                default:
                    break;
            }
        }
        amountTypeFields[RESULT_AMOUNT_TYPE_INDEX] = amountTypeFields[SUPPLY_AMOUNT_TYPE_INDEX]
                - amountTypeFields[BUY_AMOUNT_TYPE_INDEX];
    }

    private String createReport(int[] amountTypeFields) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < OPERATION_TYPE_FIELDS.length; i++) {
            stringBuilder.append(OPERATION_TYPE_FIELDS[i]).append(",")
                    .append(amountTypeFields[i]).append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

    private void writeFile(String fileName, String data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + fileName, e);
        }
    }
}
