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
    private static final String HELPER_SEPARATOR = " ";
    private static final String BUY_WORD = "buy";
    private static final String SUPPLY_WORD = "supply";


    public void getStatistic(String fromFileName, String toFileName) {

        String[] data = readFromFile(fromFileName);
        int[] results = calculateReport(data);
        makeReport(toFileName, results);
    }

    private String[] readFromFile(String fileName) {
        StringBuilder output = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            while (line != null) {
                output.append(line).append(" ");
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file: " + fileName, e);
        }

        return output.toString().split(HELPER_SEPARATOR);
    }

    private int[] calculateReport(String[] input) {
        int[] results = new int[2];
        String[] values;
        for (String line : input) {
            values = line.split(SEPARATOR);
            if (values[NAME_COLUMN].equals(SUPPLY_WORD)) {
                results[SUPPLY_INDEX] += Integer.parseInt(values[VALUE_COLUMN]);
            }
            if (values[NAME_COLUMN].equals(BUY_WORD)) {
                results[BUY_INDEX] += Integer.parseInt(values[VALUE_COLUMN]);
            }
        }
        return results;
    }

    private void makeReport(String fileName, int[] data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            StringBuilder wholeString = new StringBuilder();
            wholeString.append(makeLine("supply", data[SUPPLY_INDEX]))
                            .append(makeLine("buy", data[BUY_INDEX]))
                            .append(makeLine("result", data[SUPPLY_INDEX] - data[BUY_INDEX]));
            bufferedWriter.write(wholeString.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file: " + fileName, e);
        }
    }

    private String makeLine(String name, int value) {
        return name + SEPARATOR + value + System.lineSeparator();
    }
}
