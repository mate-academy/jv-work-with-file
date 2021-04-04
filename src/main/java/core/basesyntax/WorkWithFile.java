package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String COMMA = ",";
    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";
    public static final String RESULT = "result";
    public static final int ZERO = 0;
    public static final int ONE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        File readFromFile = new File(fromFileName);
        int supply = 0;
        int buy = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(readFromFile))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                if (value.split(COMMA)[ZERO].equals("supply")) {
                    supply += Integer.parseInt(value.split(",")[ONE]);
                }
                if (value.split(COMMA)[ZERO].equals("buy")) {
                    buy += Integer.parseInt(value.split(",")[ONE]);
                }
                value = bufferedReader.readLine();
            }
            writeToFile(toFileName, supply, buy);
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }
    }

    private void writeToFile(String toFileName, int supply, int buy) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            String result = SUPPLY + COMMA + supply + System.lineSeparator()
                    + BUY + COMMA + buy + System.lineSeparator()
                    + RESULT + COMMA + (supply - buy);
            writer.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write write data to file", e);
        }
    }
}
