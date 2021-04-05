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
    public static final int OPERATION_TYPE_POSITION = 0;
    public static final int AMOUNT_POSITION = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        File readFromFile = new File(fromFileName);
        int supply = 0;
        int buy = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(readFromFile))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                if (line.split(COMMA)[OPERATION_TYPE_POSITION].equals("supply")) {
                    supply += Integer.parseInt(line.split(",")[AMOUNT_POSITION]);
                }
                if (line.split(COMMA)[OPERATION_TYPE_POSITION].equals("buy")) {
                    buy += Integer.parseInt(line.split(COMMA)[AMOUNT_POSITION]);
                }
                line = bufferedReader.readLine();
            }
            writeToFile(toFileName, supply, buy);
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }
    }

    private void writeToFile(String toFileName, int supply, int buy) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(SUPPLY).append(COMMA).append(supply).append(System.lineSeparator())
                    .append(BUY).append(COMMA).append(buy).append(System.lineSeparator())
                    .append(RESULT).append(COMMA).append(supply - buy);
            writer.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write write data to file", e);
        }
    }
}
