package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String DIVIDE_CHARACTER = ",";
    private static final String FIRST_COLUMN_WORD = "supply";
    private static final int FIRST_COLUMN_INDEX = 0;
    private static final int SECOND_COLUMN_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        int supply = 0;
        int buy = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while (line != null) {
                String[] lineDividedByWhitespace = line.split(DIVIDE_CHARACTER);
                if (lineDividedByWhitespace[FIRST_COLUMN_INDEX].equals(FIRST_COLUMN_WORD)) {
                    supply += Integer.parseInt(lineDividedByWhitespace[SECOND_COLUMN_INDEX]);
                } else {
                    buy += Integer.parseInt(lineDividedByWhitespace[SECOND_COLUMN_INDEX]);
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        writeToFile(toFileName, supply, buy);
    }

    private void writeToFile(String toFileName, int supply, int buy) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(createReport(supply, buy));
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }

    private String createReport(int supply, int buy) {
        int result = supply - buy;
        String separator = System.lineSeparator();
        return "supply," + supply + separator + "buy," + buy + separator + "result," + result;
    }
}
