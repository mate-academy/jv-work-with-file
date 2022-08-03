package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int DATA_VALUE = 1;
    private static final String DELIMITER = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFile(fromFileName);
        String result = calculateResult(data);
        writeToFile(toFileName, result);
    }

    private String readFile(String fromFileName) {
        File inputFile = new File(fromFileName);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file:" + fromFileName, e);
        }
        return builder.toString();
    }

    private String calculateResult(String data) {
        StringBuilder builder = new StringBuilder();
        int supplyValue = 0;
        int buyValue = 0;
        String[] dataArray = data.split(System.lineSeparator());
        for (String string : dataArray) {
            if (string.contains("supply")) {
                supplyValue += Integer.parseInt(string.split(DELIMITER)[DATA_VALUE]);
            }
            if (string.contains("buy")) {
                buyValue += Integer.parseInt(string.split(DELIMITER)[DATA_VALUE]);
            }
        }
        builder.append("supply,").append(supplyValue).append(System.lineSeparator())
                .append("buy,").append(buyValue).append(System.lineSeparator())
                .append("result,").append(supplyValue - buyValue);
        return builder.toString();
    }

    private void writeToFile(String toFileName, String data) {
        File outputFile = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to the file:" + toFileName, e);
        }
    }
}
