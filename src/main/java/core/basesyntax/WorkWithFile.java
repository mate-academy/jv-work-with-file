package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private static final String SPLITTER = "\\W+";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA_SEPARATOR = ",";
    private static final int SUPPLY_POSITION = 0;
    private static final int BUY_POSITION = 1;
    private static final int RESULT_POSITION = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        writeFile(createReport(fromFileName), toFileName);
    }

    private String readFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String read = bufferedReader.readLine();
            while (read != null) {
                builder.append(read).append(System.lineSeparator());
                read = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot read file - " + fromFileName, e);
        }
        return builder.toString();
    }

    private void writeFile(String whatToWrite, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(whatToWrite);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write to file - " + toFileName, e);
        }
    }

    private int[] countStock(String fromFileName) {
        int supply = 0;
        int buy = 0;
        String[] valluesArray = readFile(fromFileName).split(SPLITTER);
        for (int i = 0; i < valluesArray.length; i = i + 2) {
            if (valluesArray[i].equals(SUPPLY)) {
                supply += Integer.parseInt(valluesArray[i + 1]);
            } else if (valluesArray[i].equals(BUY)) {
                buy += Integer.parseInt(valluesArray[i + 1]);
            }
        }
        return new int[] {supply, buy, supply - buy};
    }

    private String createReport(String fromFileName) {
        int[] stock = countStock(fromFileName);
        return SUPPLY + COMMA_SEPARATOR + stock[SUPPLY_POSITION] + System.lineSeparator()
                + BUY + COMMA_SEPARATOR + stock[BUY_POSITION] + System.lineSeparator()
                + RESULT + COMMA_SEPARATOR + stock[RESULT_POSITION] + System.lineSeparator();
    }
}
