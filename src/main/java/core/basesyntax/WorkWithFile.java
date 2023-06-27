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
    static final String SEPARATOR_FIRST = ":";
    static final String SEPARATOR_SECOND = ",";
    static final int RESET_DATA = 0;
    static final int INDEX_FIRST = 0;
    static final int INDEX_SECOND = 1;
    private int supply = 0;
    private int buy = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFile(fromFileName);
        String[] report = generateReport(data);
        writeFile(toFileName, report);
    }

    private String readFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String stringResult = "";
            String string;
            while ((string = bufferedReader.readLine()) != null) {
                stringResult += string + SEPARATOR_FIRST;
            }
            return stringResult;
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file: " + fromFileName, e);
        }
    }

    public String[] generateReport(String string) {
        String[] data = string.split(SEPARATOR_FIRST);
        for (String row: data) {
            String[] dataRow = row.split(SEPARATOR_SECOND);
            if (dataRow[INDEX_FIRST].equals(ROW_SUPPLY)) {
                supply += Integer.parseInt(dataRow[INDEX_SECOND]);
            } else {
                buy += Integer.parseInt(dataRow[INDEX_SECOND]);
            }
        }

        return new String[]{ROW_SUPPLY + "," + supply + System.lineSeparator(),
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
