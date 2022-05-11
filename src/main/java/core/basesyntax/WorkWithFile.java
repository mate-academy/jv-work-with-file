package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class WorkWithFile {

    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;
    private static final int RESULT_INDEX = 2;
    private static final int PARAM_INDEX = 0;
    private static final int VALUE_INDEX = 1;
    private static final String[] params = {"supply", "buy", "result"};

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(toFileName, getReport(calculateStatistic(readFromFile(fromFileName)
                .toString().split(System.lineSeparator()))));
    }

    public void writeToFile(String toFileName, String statistic) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(toFileName))) {
            bw.write(statistic);
        } catch (IOException e) {
            throw new RuntimeException("Can't write or close the file " + toFileName, e);
        }
    }

    public String getReport(int[] values) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < params.length; i++) {
            stringBuilder.append(params[i]).append(",").append(values[i])
                    .append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

    public int[] calculateStatistic(String[] lines) {
        int[] values = {0, 0, 0};
        int value;
        String param;
        for (String line : lines) {
            value = Integer.valueOf(line.split(",")[VALUE_INDEX]);
            param = line.split(",")[PARAM_INDEX];
            if (param.equals(params[SUPPLY_INDEX])) {
                values[SUPPLY_INDEX] += value;
            }
            if (param.equals(params[BUY_INDEX])) {
                values[BUY_INDEX] += value;
            }
        }
        values[RESULT_INDEX] = values[SUPPLY_INDEX] - values[BUY_INDEX];
        return values;
    }

    public StringBuilder readFromFile(String fileName) {
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return stringBuilder;
    }
}
