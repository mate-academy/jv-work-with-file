package core.basesyntax;

import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String SEPARATOR = ",";
    private static final int FIRST = 0;
    private static final int SECOND = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int[] data = readFromFile(fromFileName);
        writeToFile(toFileName, SUPPLY, data[FIRST], false);
        writeToFile(toFileName, BUY, data[SECOND], true);
        writeToFile(toFileName, RESULT, data[FIRST] - data[SECOND], true);
    }

    private int[] readFromFile(String fromFileName) {
        int[] result = new int[] {FIRST,FIRST};
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                String [] option = value.split(",");
                if (option[FIRST].equals(SUPPLY)) {
                    result[FIRST] += Integer.valueOf(option[SECOND]);
                } else if (option[FIRST].equals(BUY)) {
                    result[SECOND] += Integer.valueOf(option[SECOND]);
                }
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return result;
    }

    private void writeToFile(String toFileName, String name, int value, boolean append) {
        File writeFile = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(writeFile, append))) {
            writer.write(name + SEPARATOR + value + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }
}
