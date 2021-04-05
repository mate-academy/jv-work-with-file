package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String COMMA = ",";
    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";
    public static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            for (String value = bufferedReader.readLine();
                    value != null; value = bufferedReader.readLine()) {
                String[] array = value.split(COMMA);
                if (value.contains(SUPPLY)) {
                    supply += Integer.parseInt(array[1]);
                } else {
                    buy += Integer.parseInt(array[1]);
                }
            }
            StringBuilder stringBuilder = new StringBuilder()
                    .append(SUPPLY).append(COMMA).append(supply)
                    .append(System.lineSeparator())
                    .append(BUY).append(COMMA).append(buy)
                    .append(System.lineSeparator())
                    .append(RESULT).append(COMMA).append(supply - buy)
                    .append(System.lineSeparator());
            writeToFile(toFileName, stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
    }

    public void writeToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
