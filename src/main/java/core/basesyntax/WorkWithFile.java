package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final int ZERO_INDEX = 0;
    public static final int FIRST_INDEX = 1;
    public static final int AMOUNTS_INDEX = 2;
    public static final String SPLIT_PATTERN = "\\W";
    public static final String SEPARATOR = System.lineSeparator();
    public static final String BUY = "buy";
    public static final String SUPPLY = "supply";
    public static final String RESULT = "result,";
    public static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String report = createReport(readAndCount(fromFileName));
        writeToFile(report,toFileName);
    }

    private static String createReport(int[] amount) {
        return SUPPLY + COMMA + amount[FIRST_INDEX] + SEPARATOR
              + BUY + COMMA + amount[ZERO_INDEX] + SEPARATOR
              + RESULT + (amount[FIRST_INDEX] - amount[ZERO_INDEX]);
    }

    private static int[] readAndCount(String fromFileName) {
        String[] array;
        int[] amount = new int[AMOUNTS_INDEX];
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                array = value.split(SPLIT_PATTERN);
                if (array[ZERO_INDEX].equals(BUY)) {
                    amount[ZERO_INDEX] += Integer.parseInt(array[FIRST_INDEX]);
                } else if (array[ZERO_INDEX].equals(SUPPLY)) {
                    amount[FIRST_INDEX] += Integer.parseInt(array[FIRST_INDEX]);
                }
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Can’t read file", e);
        }
        return amount;
    }

    private static String writeToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can’t write to file");
        }
        return toFileName;
    }
}
