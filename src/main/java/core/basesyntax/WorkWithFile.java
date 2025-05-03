package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMMA_SEPARATOR = ",";
    private static final String READ_MESSAGE_ERROR = "Can't read from file";
    private static final String WRITE_MESSAGE_ERROR = "Can't write to file";
    private static final String SUPPLY_OPERATION_TYPE = "supply";
    private static final String BUY_OPERATION_TYPE = "buy";
    private static final String RESULT_OPERATION_TYPE = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFromFile(fromFileName);
        String[] dataFromFileArray = dataFromFile.split(System.lineSeparator());
        String dataReadyToWrite = sortDataFromFile(dataFromFileArray);
        writeToFile(toFileName, dataReadyToWrite);
    }

    private String readFromFile(String fileName) {
        File file = new File(fileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(READ_MESSAGE_ERROR, e);
        }
        return stringBuilder.toString();
    }

    private void writeToFile(String fileName, String data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException(WRITE_MESSAGE_ERROR, e);
        }
    }

    private String sortDataFromFile(String[] data) {
        String[] dataUnit;
        String buy = "buy";
        String result = "result";
        StringBuilder stringBuilder = new StringBuilder();
        int supplyAmount = 0;
        int buyAmount = 0;
        for (int i = 0; i < data.length; i++) {
            dataUnit = data[i].split(COMMA_SEPARATOR);
            if (dataUnit[0].equals(SUPPLY_OPERATION_TYPE)) {
                supplyAmount += Integer.parseInt(dataUnit[1]);
            }
            if (dataUnit[0].equals(BUY_OPERATION_TYPE)) {
                buyAmount += Integer.parseInt(dataUnit[1]);
            }
        }
        stringBuilder
                .append(SUPPLY_OPERATION_TYPE).append(COMMA_SEPARATOR)
                .append(supplyAmount).append(System.lineSeparator())
                .append(BUY_OPERATION_TYPE).append(COMMA_SEPARATOR)
                .append(buyAmount).append(System.lineSeparator())
                .append(RESULT_OPERATION_TYPE).append(COMMA_SEPARATOR)
                .append(supplyAmount - buyAmount);
        return stringBuilder.toString();
    }
}
