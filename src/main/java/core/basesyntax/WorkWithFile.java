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

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder stringBuilder = null;
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
        String[] lines = stringBuilder.toString().split(" ");
        String[][] data = new String[lines.length][2];
        int supply = 0;
        int buy = 0;
        for (int i = 0; i < lines.length; i++) {
            data[i] = lines[i].split(",");
            if (data[i][0].equals(SUPPLY)) {
                supply += Integer.parseInt(data[i][1]);
            }
            if (data[i][0].equals(BUY)) {
                buy += Integer.parseInt(data[i][1]);
            }
        }
        int result = supply - buy;
        stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY + ",").append(supply)
                .append(System.lineSeparator()).append(BUY + ",").append(buy)
                .append(System.lineSeparator()).append(RESULT + ",").append(result);
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));) {
            bufferedWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }
}
