package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int QUANTITY_INDEX = 1;
    private static final int BUYING_VALUE_INDEX = 0;
    private static final int SUPPLY_VALUE_INDEX = 1;
    private static final String COMMA_SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(toFileName, createReport(getValues(readFromFile(fromFileName))));
    }

    private String readFromFile(String fromFileName) {
        StringBuilder resultBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                resultBuilder
                        .append(value)
                        .append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't create a report for file: "
                    + fromFileName, e);
        }
        return resultBuilder.toString();
    }

    private void writeToFile(String toFileName, String message) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter
                    .write(message);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file:"
                    + toFileName
                    + " ", e);
        }
    }

    private int[] getValues(String fromFile) {
        int[] values = {0, 0};
        String[] fileValues = fromFile.split(System.lineSeparator());
        for (String line : fileValues) {
            String[] lineValues = line
                    .split(COMMA_SEPARATOR);
            switch (lineValues[OPERATION_TYPE_INDEX]) {
                case "supply":
                    values[SUPPLY_VALUE_INDEX] += Integer.parseInt(lineValues[QUANTITY_INDEX]);
                    break;
                case "buy":
                    values[BUYING_VALUE_INDEX] += Integer.parseInt(lineValues[QUANTITY_INDEX]);
                    break;
                default:
                    break;
            }
        }
        return values;
    }

    private String createReport(int[] values) {
        return "supply"
                + COMMA_SEPARATOR
                + values[SUPPLY_VALUE_INDEX]
                + System.lineSeparator()
                + "buy"
                + COMMA_SEPARATOR
                + values[BUYING_VALUE_INDEX]
                + System.lineSeparator()
                + "result"
                + COMMA_SEPARATOR
                + (values[SUPPLY_VALUE_INDEX] - values[BUYING_VALUE_INDEX]);
    }
}
