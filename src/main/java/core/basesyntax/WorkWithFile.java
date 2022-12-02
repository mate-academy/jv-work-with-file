package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_OPERATION_NAME = "supply";
    private static int SUPPLY_OPERATION_INDEX = 0;
    private static final String BUY_OPERATION_NAME = "buy";
    private static int BUY_OPERATION_INDEX = 1;
    private static final String RESULT_OPERATION_NAME = "result";
    private static final String DATA_SEPARATOR = ",";
    private static int NUMBER_OF_OPERATIONS = 2;
    private static int OPERATION_NAME_INDEX = 0;
    private static int VALUE_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        writeStatistic(toFileName, readStatistics(fromFileName));
    }

    private void writeStatistic(String fileName, int[] statistics) {
        var info = new StringBuilder(SUPPLY_OPERATION_NAME).append(DATA_SEPARATOR)
                .append(statistics[SUPPLY_OPERATION_INDEX]).append(System.lineSeparator())
                .append(BUY_OPERATION_NAME).append(DATA_SEPARATOR)
                .append(statistics[BUY_OPERATION_INDEX]).append(System.lineSeparator())
                .append(RESULT_OPERATION_NAME).append(DATA_SEPARATOR)
                .append(statistics[SUPPLY_OPERATION_INDEX] - statistics[BUY_OPERATION_INDEX])
                .toString();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(info);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + fileName, e);
        }
    }

    private int[] readStatistics(String fileName) {
        int[] statistics = new int[NUMBER_OF_OPERATIONS];
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String row = reader.readLine();
            while (row != null) {
                var values = row.split(DATA_SEPARATOR);
                if (SUPPLY_OPERATION_NAME.equals(values[OPERATION_NAME_INDEX])) {
                    statistics[SUPPLY_OPERATION_INDEX] += Integer.valueOf(values[VALUE_INDEX]);
                } else if (BUY_OPERATION_NAME.equals(values[OPERATION_NAME_INDEX])) {
                    statistics[BUY_OPERATION_INDEX] += Integer.valueOf(values[VALUE_INDEX]);
                }
                row = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fileName, e);
        }
        return statistics;
    }
}
