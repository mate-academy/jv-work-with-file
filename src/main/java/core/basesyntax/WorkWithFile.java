package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        File readFromFile = new File(fromFileName);
        int supply = 0;
        int buy = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(readFromFile))) {
            for (String line = bufferedReader.readLine();
                    line != null;
                    line = bufferedReader.readLine()) {
                if (line.split(COMMA)[OPERATION_INDEX].equals(SUPPLY)) {
                    supply += Integer.parseInt(line.split(COMMA)[AMOUNT_INDEX]);
                }
                if (line.split(COMMA)[OPERATION_INDEX].equals(BUY)) {
                    buy += Integer.parseInt(line.split(COMMA)[AMOUNT_INDEX]);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
        writeToFile(toFileName, supply, buy);
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
