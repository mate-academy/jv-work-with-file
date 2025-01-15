package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private final String[] keys = new String[]{"supply", "buy"};
    private final int[] values = new int[keys.length];

    public void getStatistic(String fromFileName, String toFileName) {
        reset();
        readFromFile(fromFileName);
        String output = calculateResult(values);
        writeToFile(output, toFileName);
    }

    private void readFromFile(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line = reader.readLine();
            while (line != null) {
                String[] keyValuePair = line.split("\\,");
                int value = Integer.parseInt(keyValuePair[1]);
                for (int i = 0; i < keys.length; i++) {
                    if (keys[i].equals(keyValuePair[0])) {
                        values[i] += value;
                        break;
                    }
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String calculateResult(int[] values) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < keys.length; i++) {
            stringBuilder.append(keys[i])
                    .append(",")
                    .append(values[i])
                    .append(System.lineSeparator());
        }
        stringBuilder.append("result,").append(values[0] - values[1]);
        return stringBuilder.toString();
    }

    private void writeToFile(String result, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void reset() {
        for (int i = 0; i < values.length; i++) {
            values[i] = 0;
        }
    }
}
