package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";
    public static final String RESULT = "result";
    public static final String COMMA = ",";
    public static final int ZERO_POSITION = 0;
    public static final int FIRST_POSITION = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        File readFromFile = new File(fromFileName);
        int supply = 0;
        int buy = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(readFromFile))) {
            for (String line = bufferedReader.readLine();
                    line != null;
                    line = bufferedReader.readLine()) {
                if (line.split(COMMA)[ZERO_POSITION].equals(SUPPLY)) {
                    supply += Integer.parseInt(line.split(COMMA)[FIRST_POSITION]);
                }
                if (line.split(COMMA)[ZERO_POSITION].equals(BUY)) {
                    buy += Integer.parseInt(line.split(COMMA)[FIRST_POSITION]);
                }
            }
            writeToFile(toFileName, supply, buy);

        } catch (IOException e1) {
            throw new RuntimeException("Can't write data to file", e1);
        }
    }

    private void writeToFile(String toFileName, int supply, int buy) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder
                    .append(SUPPLY)
                    .append(COMMA)
                    .append(supply)
                    .append(System.lineSeparator())
                    .append(BUY)
                    .append(COMMA)
                    .append(buy)
                    .append(System.lineSeparator())
                    .append(RESULT)
                    .append(COMMA)
                    .append(supply - buy);
            writer.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
