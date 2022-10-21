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
    private String data;

    public void getStatistic(String fromFileName, String toFileName) {
        String data = reedFromFile(fromFileName);
        String report = getReport(data);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName));) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }

    private String reedFromFile(String fromFileName) {
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
        return data;
    }

    private String getReport(String data) {
        int supply = 0;
        int buy = 0;
        int result;
        String[] lines = data.split(" ");
        String[][] strings = new String[lines.length][2];
        for (int i = 0; i < lines.length; i++) {
            strings[i] = lines[i].split(",");
            if (strings[i][0].equals(SUPPLY)) {
                supply += Integer.parseInt(strings[i][AMOUNT_INDEX]);
            }
            if (strings[i][0].equals(BUY)) {
                buy += Integer.parseInt(strings[i][AMOUNT_INDEX]);
            }
        }
        result = supply - buy;
        return SUPPLY + "," + supply + System.lineSeparator()
                + BUY + "," + buy + System.lineSeparator()
                + RESULT + "," + result;
    }
}
