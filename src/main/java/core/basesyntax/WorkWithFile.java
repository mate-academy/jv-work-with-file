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
    private StringBuilder stringBuilder;
    private int supply;
    private int buy;
    private int result;

    public StringBuilder redFromFile(String fromFileName, String toFileName) {
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

    public void getResult(String fromFileName, String toFileName) {
        String[] lines = redFromFile(fromFileName,toFileName).toString().split(" ");
        String[][] data = new String[lines.length][2];
        for (int i = 0; i < lines.length; i++) {
            data[i] = lines[i].split(",");
            if (data[i][0].equals(SUPPLY)) {
                supply += Integer.parseInt(data[i][1]);
            }
        }
        for (int i = 0; i < lines.length; i++) {
            data[i] = lines[i].split(",");
            if (data[i][0].equals(BUY)) {
                buy += Integer.parseInt(data[i][1]);
            }
        }
        result = supply - buy;
    }

    public void getStatistic(String fromFileName, String toFileName) {
        getResult(fromFileName, toFileName);
        String statistic = SUPPLY + "," + supply + System.lineSeparator()
                + BUY + "," + buy + System.lineSeparator()
                + RESULT + "," + result;
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));) {
            bufferedWriter.write(statistic);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }
}
