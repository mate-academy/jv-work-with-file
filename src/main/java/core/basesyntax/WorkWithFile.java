package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String COMMA = ",";
    public static final String BUY = "buy";
    public static final String SUPPLY = "supply";
    public static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        supply = readAndCalculateFile(supply, buy, fromFileName)[0];
        buy = readAndCalculateFile(supply, buy, fromFileName)[1];
        writeToFile(supply, buy, toFileName);

    }

    public int[] readAndCalculateFile(int supply, int buy, String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                if (value.contains(BUY)) {
                    buy += Integer.parseInt(value.split(COMMA)[1]);
                }
                if (value.contains(SUPPLY)) {
                    supply += Integer.parseInt(value.split(COMMA)[1]);
                }
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return new int[]{supply, buy};
    }

    public void writeToFile(int supply, int buy, String toFileName) {
        File toFile = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            bufferedWriter.write(SUPPLY + COMMA + supply
                    + System.lineSeparator()
                    + BUY + COMMA + buy
                    + System.lineSeparator()
                    + RESULT + COMMA + (supply - buy));
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
