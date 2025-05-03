package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class WorkWithFile {
    private static final String COMMA_SPLITTER = ",";
    private static final int OPERATION_NAME_INDEX = 0;
    private static final int OPERATION_AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromFile = readFromFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(report, toFileName);
    }

    private String[] readFromFile(String fileName) {
        File file = new File(fileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            return bufferedReader.lines()
                    .flatMap(line -> Arrays.stream(line.split(System.lineSeparator())))
                    .toArray(String[]::new);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file" + fileName, e);
        }
    }

    private void writeToFile(String data, String fileName) {
        File file = new File(fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file" + fileName, e);
        }
    }

    private int getOperationAmount(String[] data, String operationType) {
        int amount = 0;
        for (String datum : data) {
            String[] dataParts = datum.split(COMMA_SPLITTER);
            String operation = dataParts[OPERATION_NAME_INDEX];
            int operationAmount = Integer.parseInt(dataParts[OPERATION_AMOUNT_INDEX]);
            if (operation.equals(operationType)) {
                amount += operationAmount;
            }
        }
        return amount;
    }

    private String createReport(String[] data) {
        StringBuilder stringBuilder = new StringBuilder();
        int supply = getOperationAmount(data, "supply");
        int buy = getOperationAmount(data, "buy");
        stringBuilder.append("supply")
                .append(",")
                .append(supply)
                .append(System.lineSeparator())
                .append("buy")
                .append(",")
                .append(buy)
                .append(System.lineSeparator())
                .append("result,")
                .append(supply - buy);
        return stringBuilder.toString();
    }
}
