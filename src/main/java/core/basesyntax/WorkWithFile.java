package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private static final String READ_ERROR_MSG = "Can't read from this file: ";
    private static final String WRITE_ERROR_MSG = "Can't write to this file: ";
    private static final String SEPARATOR_REGEX = ",";
    private static final String RESULT_LINE = "result,%s";
    private static final String SUPPLY_ALWAYS_FIRST = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(report, toFileName);
    }

    private String readFile(String fromFileName) {
        StringBuilder dataFromFile = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();

            while (value != null) {
                String[] parts = value.split(SEPARATOR_REGEX);
                for (String part : parts) {
                    dataFromFile.append(part).append(SEPARATOR_REGEX);
                }
                value = reader.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(READ_ERROR_MSG + fromFileName + e);
        }

        return dataFromFile.toString();
    }

    private String createReport(String dataFromFile) {
        String[] parts = dataFromFile.split(SEPARATOR_REGEX);
        String[] keys = mergeKeys(parts);
        int[] values = mergeValues(parts, keys);
        String totalSubtraction = String.valueOf(Math.abs(values[0] - values[1]));
        StringBuilder report = new StringBuilder();

        for (int i = 0; i < keys.length; i++) {
            report.append(keys[i]).append(SEPARATOR_REGEX)
                    .append(values[i]).append(System.lineSeparator());
        }

        report.append(String.format(RESULT_LINE, totalSubtraction));
        return report.toString();
    }

    private String[] mergeKeys(String[] parts) {
        StringBuilder keysBuilder = new StringBuilder();
        String[] keysArray;

        for (String part : parts) {
            if (isWord(part)) {
                if (keysBuilder.indexOf(part) == -1) {
                    keysBuilder.append(part).append(SEPARATOR_REGEX);
                }
            }
        }

        keysArray = keysBuilder.toString().split(SEPARATOR_REGEX);
        return keysArray[0].equals(SUPPLY_ALWAYS_FIRST) ? keysArray : reverseKeys(keysArray);
    }

    private String[] reverseKeys(String[] arrayToReverse) {
        String temp = arrayToReverse[0];
        arrayToReverse[0] = arrayToReverse[1];
        arrayToReverse[1] = temp;
        return arrayToReverse;
    }

    private int[] mergeValues(String[] parts, String[] keys) {
        int[] valuesArray = new int[]{0, 0};

        for (int i = 0; i < parts.length; i++) {
            if (!isWord(parts[i])) {
                if (parts[i - 1].equals(keys[0])) {
                    valuesArray[0] += Integer.parseInt(parts[i]);
                    continue;
                }
                valuesArray[1] += Integer.parseInt(parts[i]);
            }
        }

        return valuesArray;
    }

    private boolean isWord(String part) {
        return Character.isLetter(part.charAt(0));
    }

    private void writeToFile(String text, String toFileName) {
        File file = new File(toFileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(text);
        } catch (IOException e) {
            throw new RuntimeException(WRITE_ERROR_MSG + toFileName + e);
        }
    }
}
