package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String DATA_SEPARATOR = " ";
    public static final String COMMA = ",";
    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line).append(DATA_SEPARATOR);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read this file");
        }
        int supplySum = 0;
        int buySum = 0;
        String[] dataFromFileArray = stringBuilder.toString().split(DATA_SEPARATOR);
        for (String s : dataFromFileArray) {
            String[] values = s.split(COMMA);
            if (values[0].equals(SUPPLY)) {
                supplySum += Integer.parseInt(values[1]);
            } else {
                buySum += Integer.parseInt(values[1]);
            }
        }
        StringBuilder result = new StringBuilder();
        result.append(SUPPLY).append(COMMA).append(supplySum)
                .append(System.lineSeparator())
                .append(BUY).append(COMMA).append(buySum)
                .append(System.lineSeparator())
                .append("result").append(COMMA).append(supplySum - buySum)
                .toString();

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(String.valueOf(result));
        } catch (IOException e) {
            throw new RuntimeException("Can't write this data", e);
        }
    }
}
