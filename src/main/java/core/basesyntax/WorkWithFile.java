package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMMA = ",";
    private static final String SEPARATOR = System.lineSeparator();
    private static final String BUYING_OPERATION = "buy";
    private static final String SUPPLY_OPERATION = "supply";
    private static final String RESULT = "result";
    private static final int NUMBER_OF_PARTS = 2;
    private static final int INDEX_OF_FIRST_POSITION = 0;
    private static final int INDEX_OF_SECOND_POSITION = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String fileContent = readFromFile(fromFileName);
        String report = makeReport(fileContent);
        writeToFile(toFileName,report);
    }

    private String readFromFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String value;
            while ((value = bufferedReader.readLine()) != null) {
                stringBuilder.append(value).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file" + fileName, e);
        }
        return stringBuilder.toString();
    }

    private String makeReport(String data) {
        int supplyTotal = 0;
        int buyTotal = 0;
        String[] lines = data.split(System.lineSeparator());
        for (String line : lines) {
            String[] parts = line.split(COMMA);
            if (parts.length == NUMBER_OF_PARTS) {
                String operationType = parts[INDEX_OF_FIRST_POSITION];
                int amount = Integer.parseInt(parts[INDEX_OF_SECOND_POSITION]);
                if (operationType.equals(BUYING_OPERATION)) {
                    buyTotal += amount;
                } else if (operationType.equals(SUPPLY_OPERATION)) {
                    supplyTotal += amount;
                }
            }
        }
        int result = supplyTotal - buyTotal;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY_OPERATION).append(COMMA).append(supplyTotal).append(SEPARATOR)
                .append(BUYING_OPERATION).append(COMMA).append(buyTotal)
                .append(SEPARATOR).append(RESULT)
                .append(COMMA).append(result);
        return stringBuilder.toString();
    }

    private void writeToFile(String toFile,String data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file: " + toFile, e);
        }
    }
}
