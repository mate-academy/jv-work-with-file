package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final int AMOUNT_INDEX = 1;
    private static final int OPERATION_INDEX = 0;
    private String data;
    private String report;

    public void getStatistic(String fromFileName, String toFileName) {
        reedFromFile(fromFileName);
        getReport(data);
        writeToFile(report, toFileName);
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName));) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }

    private void reedFromFile(String fromFileName) {
        StringBuilder stringBuilder;
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(fromFileName));
            stringBuilder = new StringBuilder();
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(" ");
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
        data = stringBuilder.toString();
    }

    private void getReport(String data) {
        int supply = 0;
        int buy = 0;
        int result;
        String[] lines = data.split(" ");
        String[][] strings = new String[lines.length][2];
        for (int i = 0; i < lines.length; i++) {
            strings[i] = lines[i].split(",");
            if (strings[i][OPERATION_INDEX].equals(SUPPLY)) {
                supply += Integer.parseInt(strings[i][AMOUNT_INDEX]);
            }
            if (strings[i][OPERATION_INDEX].equals(BUY)) {
                buy += Integer.parseInt(strings[i][AMOUNT_INDEX]);
            }
        }
        result = supply - buy;
        report = SUPPLY + "," + supply + System.lineSeparator()
                + BUY + "," + buy + System.lineSeparator()
                + RESULT + "," + result;
    }
}
