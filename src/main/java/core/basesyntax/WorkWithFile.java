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

    public void getStatistic(String fromFileName, String toFileName) {
        String line;
        int[] values = {0, 0, 0};
        int value;
        String param;
        try (BufferedReader br = new BufferedReader(new FileReader(fromFileName))) {
            while ((line = br.readLine()) != null) {
                value = Integer.valueOf(line.split(",")[VALUE_INDEX]);
                param = line.split(",")[PARAM_INDEX];
                if (param.equals("supply")) {
                    values[SUPPLY_INDEX] += value;
                }
                if (param.equals("buy")) {
                    values[BUY_INDEX] += value;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fromFileName, e);
        }
        values[RESULT_INDEX] = values[SUPPLY_INDEX] - values[BUY_INDEX];
        writeToFile(toFileName, values);
    }

    public void writeToFile(String toFileName, int[] values) {
        String[] params = {"supply", "buy", "result"};
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < params.length; i++) {
            stringBuilder.append(params[i]).append(",").append(values[i])
                    .append(System.lineSeparator());
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(toFileName))) {
            bw.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write or close the file " + toFileName, e);
        }
    }
}
