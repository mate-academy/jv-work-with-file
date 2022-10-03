package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String CSV_SEPARATOR = ",";
    private static final String SUPPLY_OPERATION_NAME = "supply";
    private static final String BUY_OPERATION_NAME = "buy";
    private static final String RESULT_NAME = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] readData = readFromFile(fromFileName).split(System.lineSeparator());
        writeToFile(toFileName, generateStatistic(readData));
    }

    private String generateStatistic(String[] data) {
        StringBuilder statistic = new StringBuilder();
        int supplyNumber = 0;
        int buyNumber = 0;

        for (String datum : data) {
            String operationType = datum.split(CSV_SEPARATOR)[0];
            int number = Integer.parseInt(datum.split(CSV_SEPARATOR)[1]);

            if (operationType.equals(SUPPLY_OPERATION_NAME)) {
                supplyNumber += number;
            } else {
                buyNumber += number;
            }
        }

        statistic.append(SUPPLY_OPERATION_NAME).append(CSV_SEPARATOR)
                .append(supplyNumber).append(System.lineSeparator())
                .append(BUY_OPERATION_NAME).append(CSV_SEPARATOR)
                .append(buyNumber).append(System.lineSeparator())
                .append(RESULT_NAME).append(CSV_SEPARATOR)
                .append(supplyNumber - buyNumber);

        return statistic.toString();
    }

    private String readFromFile(String fileName) {
        StringBuilder data = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            while (bufferedReader.ready()) {
                data.append(bufferedReader.readLine()).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read " + fileName, e);
        }

        return data.toString();
    }

    private void writeToFile(String fileName, String data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to " + fileName, e);
        }
    }
}
