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
    public static final String RESULT = "result";
    public static final String DELIMITER = "\\W+";
    public static final String COMMA = ",";
    public static final String SEPARATOR = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) {
        writeList(createReport(readList(fromFileName)),toFileName);
    }

    private String readList(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append(COMMA);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read: " + fromFileName);
        }
        return stringBuilder.toString();
    }

    private String createReport(String list) {
        String[] all = list.split(DELIMITER);
        int[] operationSum = new int[INDEX_SUM_ARRAY_LENGTH];
        for (int j = 0; j < all.length; j += 2) {
            if (all[j].equals(SUPPLY)) {
                operationSum[INDEX_SUPPLY] += Integer.parseInt(all[j + ARRAY_COUNT]);
            } else if (all[j].equals(BUY)) {
                operationSum[INDEX_BUY] += Integer.parseInt(all[j + ARRAY_COUNT]);
            }
        }
        return SUPPLY + COMMA + operationSum[INDEX_SUPPLY] + SEPARATOR
                + BUY + COMMA + operationSum[INDEX_BUY] + SEPARATOR
                + RESULT + COMMA + (operationSum[INDEX_SUPPLY] - operationSum[INDEX_BUY]);
    }

    private void writeList(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write: " + toFileName);
        }
    }
}
