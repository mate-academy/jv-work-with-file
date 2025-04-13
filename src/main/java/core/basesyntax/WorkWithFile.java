package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMMA = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));) {

            String line = bufferedReader.readLine();
            while (line != null) {
                String[] spllitedLine = line.split(COMMA);

                if (SUPPLY.equals(spllitedLine[0])) {
                    supply += Integer.parseInt(spllitedLine[1]);
                } else {
                    buy += Integer.parseInt(spllitedLine[1]);
                }
                line = bufferedReader.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException("Cant read or write file", e);
        }

        writeToFile(buy, supply, toFileName);
    }

    public void writeToFile(int buy, int supply, String file) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));) {
            bufferedWriter.write(SUPPLY + COMMA + supply + System.lineSeparator()
                    + BUY + COMMA + buy + System.lineSeparator()
                    + RESULT + COMMA + (supply - buy));
        } catch (IOException e) {
            throw new RuntimeException("File writing error", e);
        }

    }
}
