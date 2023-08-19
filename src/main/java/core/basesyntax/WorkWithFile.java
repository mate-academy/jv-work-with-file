package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;
    private static final int NAME_COLUMN = 0;
    private static final int VALUE_COLUMN = 1;
    private static final String SEPARATOR = ",";
    private static final String BUY_WORD = "buy";
    private static final String SUPPLY_WORD = "supply";

    public void getStatistic(String fromFileName, String toFileName) {

        int[] data = parseAndAggregate(fromFileName);
        makeReport(toFileName, data);
    }

    public int[] parseAndAggregate(String fileName) {
        int[] results = new int[2];

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String value = reader.readLine();
            String[] values;
            while (value != null) {
                values = value.split(SEPARATOR);
                if (values[NAME_COLUMN].equals(SUPPLY_WORD)) {
                    results[SUPPLY_INDEX] += Integer.parseInt(values[VALUE_COLUMN]);
                }
                if (values[NAME_COLUMN].equals(BUY_WORD)) {
                    results[BUY_INDEX] += Integer.parseInt(values[VALUE_COLUMN]);
                }
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }

        return results;
    }

    public void makeReport(String fileName, int[] data) {

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(makeLine("supply", data[SUPPLY_INDEX]));
            bufferedWriter.write(makeLine("buy", data[BUY_INDEX]));
            bufferedWriter.write(makeLine("result", data[SUPPLY_INDEX] - data[BUY_INDEX]));
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file", e);
        }
    }

    public String makeLine(String name, int value) {
        return name + "," + value + System.lineSeparator();
    }
}
