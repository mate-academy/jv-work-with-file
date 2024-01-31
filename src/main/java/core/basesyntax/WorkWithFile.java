package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {

    private static final String ERROR_MSG = "Can't read from file.";
    private static final String SEPARATOR_REGEX = ",";
    private static final int VALUE_INDEX = 0;
    private static final int KEY_INDEX = 1;
    private static final String RESULT_LINE = "result,%s";
    private static final String SUPPLY_ALWAYS_FIRST = "supply";
    private List<String[]> linesArrays;
    private String[] values;
    private int[] keys;
    private StringBuilder finalResults;

    public void getStatistic(String fromFileName, String toFileName) {
        linesArrays = new ArrayList<>();
        finalResults = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();

            while (value != null) {
                linesArrays.add(value.split(SEPARATOR_REGEX));
                value = reader.readLine();
            }
            values = mergeValues(linesArrays);
            keys = mergeKeys(linesArrays, values);
            finalResults = calculateResults(values, keys);
        } catch (IOException e) {
            throw new RuntimeException(ERROR_MSG + e);
        }

        writeToFile(finalResults, toFileName);
    }

    private String[] mergeValues(List<String[]> linesArrays) {
        List<String> mergedValues = new ArrayList<>();

        for (String[] line : linesArrays) {
            if (!mergedValues.contains(line[VALUE_INDEX])) {
                mergedValues.add(line[VALUE_INDEX]);
            }
        }

        String temp = mergedValues.get(0); // Getting temp to reverse if needed
        if (!temp.equals(SUPPLY_ALWAYS_FIRST)) {
            mergedValues.set(0, mergedValues.get(1));
            mergedValues.set(1, temp);
        }
        return mergedValues.toArray(new String[]{});
    }

    private int[] mergeKeys(List<String[]> linesArrays, String[] values) {
        int[] mergedKeys = new int[values.length];

        for (String[] line : linesArrays) {
            int key = Integer.parseInt(line[KEY_INDEX]);
            int index = line[VALUE_INDEX].equals(values[VALUE_INDEX]) ? 0 : 1;
            mergedKeys[index] += key;
        }

        return mergedKeys;
    }

    private StringBuilder calculateResults(String[] values, int[] keys) {
        StringBuilder builder = new StringBuilder();
        String totalSubtraction = "";
        totalSubtraction = String.valueOf(Math.abs(keys[0] - keys[1]));

        for (int i = 0; i < values.length; i++) {
            builder.append(values[i]).append(SEPARATOR_REGEX)
                    .append(keys[i]).append(System.lineSeparator());
        }

        builder.append(String.format(RESULT_LINE, totalSubtraction));
        return builder;
    }

    private void writeToFile(StringBuilder builder, String toFileName) {
        File file = new File(toFileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(builder.toString());
        } catch (IOException e) {
            throw new RuntimeException(ERROR_MSG + e);
        }
    }
}
