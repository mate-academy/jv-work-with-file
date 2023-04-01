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
    private static final String SUPPLY_FIELD_NAME = "supply";
    private static final String BUY_FIELD_NAME = "buy";
    private static final String RESULT_FIELD_NAME = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(toFileName, processData(readDataFromFile(fromFileName)));
    }

    private String[] readDataFromFile(String nameFile) {
        File file = new File(nameFile);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cant read file", e);
        }
        return stringBuilder.toString().split(System.lineSeparator());
    }

    private String processData(String[] data) {
        int totalSupply = 0;
        int totalBuy = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (String record : data) {
            String[] parsedRecord = record.split(DATA_FIELD_SEPARATOR);
            if (parsedRecord[OPERATION_TYPE_INDEX].equals(SUPPLY_FIELD_NAME)) {
                totalSupply += Integer.parseInt(parsedRecord[AMOUNT_INDEX]);
            } else if (parsedRecord[OPERATION_TYPE_INDEX].equals(BUY_FIELD_NAME)) {
                totalBuy += Integer.parseInt(parsedRecord[AMOUNT_INDEX]);
            }
        }
        int result = totalSupply - totalBuy;
        return stringBuilder.append(SUPPLY_FIELD_NAME).append(DATA_FIELD_SEPARATOR)
                .append(totalSupply).append(System.lineSeparator())
                .append(BUY_FIELD_NAME).append(DATA_FIELD_SEPARATOR)
                .append(totalBuy).append(System.lineSeparator())
                .append(RESULT_FIELD_NAME).append(DATA_FIELD_SEPARATOR).append(result).toString();

    }

    private void writeToFile(String toFile, String fromData) {
        File fileOut = new File(toFile);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileOut))) {
            bufferedWriter.write(fromData);
        } catch (IOException e) {
            throw new RuntimeException("Cant write file", e);
        }
    }
}
