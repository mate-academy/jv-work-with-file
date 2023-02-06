package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final int INDEX_SUPPLY = 0;
    public static final int INDEX_BUY = 1;
    public static final int INDEX_SUM_ARRAY_LENGTH = 2;
    public static final int ARRAY_COUNT = 1;
    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";
    public static final String TOTAL = "result";
    public static final String DELIMITER = "\\W+";
    public static final String SEPARATOR = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) {
        writeList(fileWork(readList(fromFileName)),toFileName);
    }

    private String readList(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            int value = reader.read();
            while (value != -1) {
                stringBuilder.append((char) value);
                value = reader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read: " + fromFileName);
        }
        return stringBuilder.toString();
    }

    private StringBuilder fileWork(String list) {
        String[] all = list.split(DELIMITER);
        int[] operationSum = new int[INDEX_SUM_ARRAY_LENGTH];
        for (int j = 0; j < all.length; j += 2) {
            if (all[j].equals(SUPPLY)) {
                operationSum[INDEX_SUPPLY] += Integer.parseInt(all[j + ARRAY_COUNT]);
            } else if (all[j].equals(BUY)) {
                operationSum[INDEX_BUY] += Integer.parseInt(all[j + ARRAY_COUNT]);
            }
        }
        int result = operationSum[INDEX_SUPPLY] - operationSum[INDEX_BUY];
        StringBuilder writer = new StringBuilder().append(SUPPLY).append(",");
        writer.append(operationSum[INDEX_SUPPLY]).append(SEPARATOR).append(BUY).append(",");
        writer.append(operationSum[INDEX_BUY]).append(SEPARATOR).append(TOTAL).append(",");
        writer.append(result);
        return writer;
    }

    private void writeList(StringBuilder writer, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(String.valueOf(writer));
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException("Can't write: " + toFileName);
        }
    }
}
