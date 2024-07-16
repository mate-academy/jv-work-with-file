package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int[] output = readFile(fromFileName);
        String result = getResult(output);
        writeFile(result, toFileName);
    }

    public void writeFile(String data, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, true))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + toFileName, e);
        }
    }

    public int[] readFile(String fromFileName) {
        int supplyResult = 0;
        int buyResult = 0;
        int result = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                String amount = value.substring(value.indexOf(",") + 1);
                if (value.contains("supply")) {
                    supplyResult += Integer.parseInt(amount);
                } else if (value.contains("buy")) {
                    buyResult += Integer.parseInt(amount);
                }
                result = supplyResult - buyResult;
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return new int[] {supplyResult, buyResult, result};
    }

    public String getResult(int[] data) {
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
