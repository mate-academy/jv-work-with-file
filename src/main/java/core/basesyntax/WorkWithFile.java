package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    static final String ROW_SUPPLY = "supply";
    static final String ROW_BUY = "buy";
    static final String ROW_RESULT = "result";
    static final String SEPARATOR = ",";
    static final int RESET_DATA = 0;
    static final int INDEX_FIRST = 0;
    static final int INDEX_SECOND = 1;
    private int supply = 0;
    private int buy = 0;
    private String[] result;

    public void getStatistic(String fromFileName, String toFileName) {
        readFile(fromFileName);
        writeFile(toFileName, result);
    }

    private void readFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String strings;
            while ((strings = bufferedReader.readLine()) != null) {
                createReport(strings);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file: " + fromFileName, e);
        }
    }

    public void createReport(String string) {
        String[] dataRow = string.split(SEPARATOR);
        if (dataRow[INDEX_FIRST].equals(ROW_SUPPLY)) {
            supply += Integer.parseInt(dataRow[INDEX_SECOND]);
        } else {
            buy += Integer.parseInt(dataRow[INDEX_SECOND]);
        }

        result = new String[]{ROW_SUPPLY + "," + supply + System.lineSeparator(),
                ROW_BUY + "," + buy + System.lineSeparator(),
                ROW_RESULT + "," + (supply - buy)};
    }

    public void writeFile(String toFileName, String[] result) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            for (String row : result) {
                bufferedWriter.write(row);
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file: " + toFileName, e);
        }
        buy = RESET_DATA;
        supply = RESET_DATA;
    }
}
