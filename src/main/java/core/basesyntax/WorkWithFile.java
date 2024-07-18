package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String[] strings = readFile(fromFileName);
        int[] output = dataFile(strings);
        String result = getResult(output);
        writeFile(result, toFileName);
    }

    private void writeFile(String data, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, true))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + toFileName, e);
        }
    }

    private String[] readFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return stringBuilder.toString().split(System.lineSeparator());
    }

    private int[] dataFile(String[] arrays) {
        int supplyResult = 0;
        int buyResult = 0;
        int result = 0;
        for (int i = 0; i < arrays.length; i++) {
            String substring = arrays[i].substring(0, arrays[i].indexOf(","));
            if (substring.equals("supply")) {
                supplyResult += Integer.parseInt(arrays[i].substring(arrays[i].indexOf(",") + 1));
            } else if (substring.equals("buy")) {
                buyResult += Integer.parseInt(arrays[i].substring(arrays[i].indexOf(",") + 1));
            }
            result = supplyResult - buyResult;
        }
        return new int[] {supplyResult, buyResult, result};
    }

    private String getResult(int[] data) {
        int supplyResult = data[0];
        int buyResult = data[1];
        int result = data[2];
        StringBuilder builder = new StringBuilder();
        builder.append("supply").append(",").append(supplyResult).append(System.lineSeparator())
                .append("buy").append(",").append(buyResult).append(System.lineSeparator())
                .append("result").append(",").append(result);
        return builder.toString();
    }
}
