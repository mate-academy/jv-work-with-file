package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private static final String COMMA_SEPARATOR = ",";
    private static final int OPERATION_TYPE_POSITION = 0;
    private static final int AMOUNT_POSITION = 1;
    private static final OperationType SUPPLY = OperationType.SUPPLY;
    private static final OperationType BUY = OperationType.BUY;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = getDataFromFile(fromFileName);
        writeDataToFile(toFileName, createReport(data));
    }

    private String[] getDataFromFile(String fileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
            return stringBuilder.toString().split(System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }
    }

    private void writeDataToFile(String fileName, String data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }

    private String createReport(String[] data) {
        int supplyAmount = 0;
        int buyAmount = 0;
        for (String dataObject : data) {
            String[] splittedDataString = dataObject.split(COMMA_SEPARATOR);
            if (splittedDataString[OPERATION_TYPE_POSITION]
                    .equals(SUPPLY.toString().toLowerCase())) {
                supplyAmount += Integer.parseInt(splittedDataString[AMOUNT_POSITION]);
            }
            if (splittedDataString[OPERATION_TYPE_POSITION]
                    .equals(BUY.toString().toLowerCase())) {
                buyAmount += Integer.parseInt(splittedDataString[AMOUNT_POSITION]);
            }
        }
        int result = supplyAmount - buyAmount;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(OperationType.SUPPLY.toString().toLowerCase())
                .append(COMMA_SEPARATOR).append(supplyAmount);
        stringBuilder.append(System.lineSeparator())
                .append(OperationType.BUY.toString().toLowerCase())
                .append(COMMA_SEPARATOR).append(buyAmount);
        stringBuilder.append(System.lineSeparator())
                .append(OperationType.RESULT.toString().toLowerCase())
                .append(COMMA_SEPARATOR).append(result);
        return stringBuilder.toString();
    }
}
