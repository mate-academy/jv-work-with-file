package core.basesyntax;

import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;

public class WorkWithFile {
    private static final String BUY_VALUE = "buy";
    private static final String SUPPLY_VALUE = "supply";
    private static final String RESULT_VALUE = "result";
    private static final int NAME_INDEX = 0;
    private static final int NUMBER_INDEX = 1;
    private static final String SPACE_SEPARATOR = " ";
    private static final String COMMA_SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromFile = readFile(fromFileName);
        String generatedReport = generateReport(dataFromFile);
        writeToFile(toFileName, generatedReport);
    }

    public String[] readFile(String fromFileName) {
        StringBuilder resultBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader
                    .readLine();
            while (String.valueOf(value) != null) {
                resultBuilder
                        .append(value)
                        .append(SPACE_SEPARATOR);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read to file" + fromFileName, e);
        }
        return resultBuilder
                .toString()
                .split(SPACE_SEPARATOR);
    }

    private String generateReport(String[] fromFileDate) {
        int buyValue = 0;
        int supplyValue = 0;
        for (String line: fromFileDate) {
            String[] words = line.split(COMMA_SEPARATOR);
            if (words[NAME_INDEX].equals(BUY_VALUE)) {
                buyValue += Integer
                        .parseInt(words[NUMBER_INDEX]);
            }
            if (words[NAME_INDEX].equals(SUPPLY_VALUE)) {
                supplyValue += Integer
                        .parseInt(words[NUMBER_INDEX]);
            }
        }
        return SUPPLY_VALUE +
                COMMA_SEPARATOR +
                supplyValue + System.lineSeparator() +
                BUY_VALUE +
                COMMA_SEPARATOR +
                buyValue +
                System.lineSeparator() +
                RESULT_VALUE +
                COMMA_SEPARATOR +
                (supplyValue - buyValue) +
                System.lineSeparator();
    }

    private void writeToFile(String toFileName, String reportString) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(reportString);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write the file: " + reportString, e);
        }
    }
 }
