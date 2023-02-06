package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final int INDEX_1 = 0;
    public static final int INDEX_2 = 1;
    public static final int INDEX_3 = 2;
    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";
    public static final String TOTAL = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        BufferedReader reader;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            reader = new BufferedReader(new FileReader(fromFileName));
            int value = reader.read();
            while (value != -1) {
                stringBuilder.append((char) value);
                value = reader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file!", e);
        }
        String list = stringBuilder.toString();
        String[] all = list.split("\\W+");
        int [] operationSum = new int[INDEX_3];
        for (int j = 0; j < all.length; j += 2) {
            if (all[j].equals(SUPPLY)) {
                operationSum[INDEX_1] += Integer.parseInt(all[j + INDEX_2]);
            } else if (all[j].equals(BUY)) {
                operationSum[INDEX_2] += Integer.parseInt(all[j + INDEX_2]);
            }
        }
        int result = operationSum[INDEX_1] - operationSum[INDEX_2];
        StringBuilder writer = new StringBuilder();
        writer.append(SUPPLY).append(",").append(operationSum[INDEX_1]).append("\r\n");
        writer.append(BUY).append(",").append(operationSum[INDEX_2]).append("\r\n");
        writer.append(TOTAL).append(",").append(result);
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName));
            bufferedWriter.write(String.valueOf(writer));
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't read file!", e);
        }
    }
}
