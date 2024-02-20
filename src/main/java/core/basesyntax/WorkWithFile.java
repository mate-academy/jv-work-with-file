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

    public void getStatistic(String fromFileName, String toFileName) {
        String readData = readFromFile(fromFileName);
        String report = makeReport(readData);
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
            throw new RuntimeException("Can't read data from file", e);
        }
        return stringBuilder.toString();
    }

    private String makeReport(String data) {
        int supplyTotal = 0;
        int buyTotal = 0;
        String[] lines = data.split(System.lineSeparator());
        for (String line : lines) {
            String[] parts = line.split(COMMA);
            if (parts.length == 2) {
                String operationType = parts[0];
                int amount = Integer.parseInt(parts[1]);
                if (operationType.equals(BUYING_OPERATION)) {
                    buyTotal += amount;
                } else if (operationType.equals(SUPPLY_OPERATION)) {
                    supplyTotal += amount;
                }
            }
        }
        int result = supplyTotal - buyTotal;
        return SUPPLY_OPERATION + COMMA + supplyTotal + SEPARATOR +
                BUYING_OPERATION + COMMA + buyTotal + SEPARATOR + RESULT +
                COMMA + result;
    }

    private void writeToFile(String toFile,String data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
