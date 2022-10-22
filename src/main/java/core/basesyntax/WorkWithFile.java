package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String DATA_FIELD_SEPARATOR = ",";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromMarket = getDataFromFile(fromFileName);
        String report = createReport(dataFromMarket);
        writeDataToFile(report, toFileName);
    }

    private String[] getDataFromFile(String fileName) {
        final StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fileName, e);
        }
        return stringBuilder.toString().split(System.lineSeparator());
    }

    private String createReport(String[] data) {
        final String supplyFieldName = "supply";
        final String buyFieldName = "buy";
        final String resultFieldName = "result";
        int totalSupply = 0;
        int totalBuy = 0;
        final StringBuilder stringBuilder = new StringBuilder();
        for (String record : data) {
            String[] parsedRecord = record.split(DATA_FIELD_SEPARATOR);
            if (parsedRecord[OPERATION_TYPE_INDEX].equals(supplyFieldName)) {
                totalSupply += Integer.parseInt(parsedRecord[AMOUNT_INDEX]);
            } else if (parsedRecord[OPERATION_TYPE_INDEX].equals(buyFieldName)) {
                totalBuy += Integer.parseInt(parsedRecord[AMOUNT_INDEX]);
            }
        }
        int result = totalSupply - totalBuy;
        return stringBuilder.append(supplyFieldName).append(DATA_FIELD_SEPARATOR)
                .append(totalSupply).append(System.lineSeparator())
                .append(buyFieldName).append(DATA_FIELD_SEPARATOR)
                .append(totalBuy).append(System.lineSeparator())
                .append(resultFieldName).append(DATA_FIELD_SEPARATOR).append(result).toString();
    }

    private void writeDataToFile(String data, String fileName) {
        File file = new File(fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + fileName, e);
        }
    }
}
