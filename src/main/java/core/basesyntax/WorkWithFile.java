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

    public void getStatistic(String fromFileName, String toFileName) {
        String report = createReport(readAndCount(fromFileName));
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can’t write to file");
        }
    }

    private static String createReport(int[] amount) {
        return "supply," + amount[FIRST_INDEX] + SEPARATOR
              + "buy," + amount[ZERO_INDEX] + SEPARATOR
              + "result," + (amount[FIRST_INDEX] - amount[ZERO_INDEX]);
    }

    private static int[] readAndCount(String fromFileName) {
        String[] array;
        int[] amount = new int[AMOUNTS_INDEX];
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                array = value.split(SPLIT_PATTERN);
                if (array[ZERO_INDEX].equals("buy")) {
                    amount[ZERO_INDEX] += Integer.parseInt(array[FIRST_INDEX]);
                } else if (array[ZERO_INDEX].equals("supply")) {
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
}
