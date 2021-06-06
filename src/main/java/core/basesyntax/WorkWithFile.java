package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String SEPARATOR = ",";
    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";
    public static final String RESULT = "result";
    public static final int INDEX_OF_VALUE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains(SUPPLY)) {
                    supply += Integer.parseInt(line.split(SEPARATOR)[INDEX_OF_VALUE]);
                }
                if (line.contains(BUY)) {
                    buy += Integer.parseInt(line.split(SEPARATOR)[INDEX_OF_VALUE]);
                }
            }
            stringBuilder.append(SUPPLY).append(SEPARATOR).append(supply)
                    .append(System.lineSeparator())
                    .append(BUY).append(SEPARATOR).append(buy)
                    .append(System.lineSeparator())
                    .append(RESULT).append(SEPARATOR).append(supply - buy);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file", e);
        }
        writeToFile(toFileName, stringBuilder.toString());
    }

    public void writeToFile(String toFileName, String data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
