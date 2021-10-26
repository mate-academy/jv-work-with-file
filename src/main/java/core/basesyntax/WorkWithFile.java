package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final char COMA = ',';

    public static void getStatistic(String fromFileName, String toFileName) {
        String[] s = readFromFile(fromFileName).split(" ");
        statistic(s);
        String stat = statistic(s);
        writeToFile(fromFileName, toFileName, stat);
    }

    private static String statistic(String[] s) {

        int supply = 0;
        int buy = 0;
        int result = 0;
        for (int i = 0; i < s.length; i++) {
            int j = s[i].indexOf(COMA);
            if (s[i].substring(0, j).equals(SUPPLY)) {
                supply += Integer.valueOf(s[i].substring(j + 1));
            }
            if (s[i].substring(0, j).equals(BUY)) {
                buy += Integer.valueOf(s[i].substring(j + 1));
            }
        }
        result = supply - buy;
        StringBuilder builder = new StringBuilder();
        builder.append(SUPPLY).append(COMA).append(supply).append("\n")
                .append(BUY).append(COMA).append(buy).append('\n')
                .append(RESULT).append(COMA).append(result);
        return builder.toString();
    }

    private static String readFromFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(" ");
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fromFileName, e);
        }
        return builder.toString();
    }

    private static void writeToFile(String fromFileName, String toFileName, String stat) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(stat);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to File", e);
        }
    }
}
