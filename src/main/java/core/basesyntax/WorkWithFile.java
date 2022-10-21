package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));) {
            bufferedWriter.write(getReport(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }

    private StringBuilder redFromFile(String fromFileName) {
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
        return stringBuilder;
    }

    private String getReport(String fromFileName) {
        int supply = 0;
        int buy = 0;
        int result;
        String[] lines = redFromFile(fromFileName).toString().split(" ");
        String[][] data = new String[lines.length][2];
        for (int i = 0; i < lines.length; i++) {
            data[i] = lines[i].split(",");
            if (data[i][0].equals(SUPPLY)) {
                supply += Integer.parseInt(data[i][AMOUNT_INDEX]);
            }
            if (data[i][0].equals(BUY)) {
                buy += Integer.parseInt(data[i][AMOUNT_INDEX]);
            }
        }
        result = supply - buy;
        return SUPPLY + "," + supply + System.lineSeparator()
                + BUY + "," + buy + System.lineSeparator()
                + RESULT + "," + result;
    }
}
