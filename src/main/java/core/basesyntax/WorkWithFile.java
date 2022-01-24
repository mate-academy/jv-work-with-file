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
    private static final String COMMA_SEPARATOR = ",";

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

    private String createReport(String fromFileName) {
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
        return SUPPLY + COMMA_SEPARATOR + supply + System.lineSeparator()
                + BUY + COMMA_SEPARATOR + buy + System.lineSeparator()
                + "result" + COMMA_SEPARATOR + (supply - buy) + System.lineSeparator();
    }
}
