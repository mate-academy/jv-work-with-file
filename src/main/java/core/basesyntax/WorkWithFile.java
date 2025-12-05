package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final int ACTION = 0;
    private static final int NUMBER = 1;
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] arr = readFromFile(fromFileName).split(LINE_SEPARATOR);
        int buy = 0;
        int supply = 0;
        for (String s : arr) {
            String[] parts = s.split(",");
            if (parts[ACTION].equals(BUY)) {
                buy += Integer.parseInt(parts[NUMBER]);
            } else {
                supply += Integer.parseInt(parts[NUMBER]);
            }
        }
        int res = supply - buy;
        writeIntoFile(supply, buy, res, toFileName);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder text = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                text.append(line).append(LINE_SEPARATOR);
            }
        } catch (Exception e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
        return text.toString();
    }

    private void writeIntoFile(int supply, int buy, int res, String toFileName) {
        StringBuilder results = new StringBuilder();
        results.append(SUPPLY).append(",").append(supply)
                .append(LINE_SEPARATOR).append(BUY).append(",")
                .append(buy).append(LINE_SEPARATOR).append(RESULT)
                .append(",").append(res);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(results.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }
}
