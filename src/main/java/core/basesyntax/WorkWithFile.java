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
    private static final String COMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader(fromFileName)); BufferedWriter bufferedWriter = new BufferedWriter(
                            new FileWriter(toFileName))) {
            int supply = 0;
            int buy = 0;
            String value;
            while ((value = bufferedReader.readLine()) != null) {
                if (value.contains(SUPPLY)) {
                    supply += Integer.parseInt(value.substring(value.lastIndexOf(COMA) + 1));
                } else if (value.contains(BUY)) {
                    buy += Integer.parseInt(value.substring(value.lastIndexOf(COMA) + 1));
                }
            }
            stringBuilder.append(SUPPLY).append(COMA).append(supply).append(System.lineSeparator())
                    .append(BUY).append(COMA).append(buy).append(System.lineSeparator())
                    .append(RESULT).append(COMA).append(supply - buy);
            bufferedWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("File can't be read " + fromFileName, e);
        }
    }
}
