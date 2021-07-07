package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String OPERATION_SEPARATOR = ",";
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) {
        String fileData = readFromFile(fromFileName);
        String[] basicOperationArray = fileData.split(LINE_SEPARATOR);
        OperationTable operationTable = convertOperationArrayToOperationTable(basicOperationArray);
        operationTable.sumUpTable();
        String resultText = operationTable.toString();
        writeToFile(toFileName, resultText);
    }

    private String readFromFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(LINE_SEPARATOR);
                value = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find file ", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file ", e);
        }
        return stringBuilder.toString();
    }

    private OperationTable convertOperationArrayToOperationTable(String[] basicOperationArray) {
        //array for holding "operation_type" [0] and "amount" [1]
        OperationTable operationTable = new OperationTable(basicOperationArray.length);

        //split file line with separator and write both parts to the operationTable
        for (int i = 0; i < basicOperationArray.length; i++) {
            int splitSymbol = basicOperationArray[i].indexOf(OPERATION_SEPARATOR);
            if (splitSymbol == -1) {
                throw new RuntimeException("Invalid data in row [" + i + "]");
            }
            try {
                operationTable.addRow(basicOperationArray[i].substring(0, splitSymbol),
                        Integer.parseInt(basicOperationArray[i].substring(splitSymbol + 1)));
            } catch (NumberFormatException e) {
                throw new RuntimeException("Can't convert amount in row " + i + " to integer");
            }
        }
        return operationTable;
    }

    private void writeToFile(String toFileName, String resultText) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            for (int i = 0; i < resultText.length(); i++) {
                bufferedWriter.append(resultText.charAt(i));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find file ", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file ", e);
        }
    }
}
