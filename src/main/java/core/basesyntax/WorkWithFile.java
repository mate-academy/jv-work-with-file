package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String CSV_SEPARATOR = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final int AMOUNT_INDEX = 1;
    private static final int OPERATION_INDEX = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            for (String value = bufferedReader.readLine();
                    value != null; value = bufferedReader.readLine()) {
                String[] array = value.split(CSV_SEPARATOR);
                if (array[OPERATION_INDEX].equals(SUPPLY)) {
                    supply += Integer.parseInt(array[AMOUNT_INDEX]);
                } else {
                    buy += Integer.parseInt(array[AMOUNT_INDEX]);
                }
            }
            stringBuilder = stringBuilder.append(SUPPLY)
                    .append(CSV_SEPARATOR).append(supply)
                    .append(System.lineSeparator())
                    .append(BUY).append(CSV_SEPARATOR).append(buy)
                    .append(System.lineSeparator())
                    .append(RESULT).append(CSV_SEPARATOR).append(supply - buy)
                    .append(System.lineSeparator());
            writeToFile(toFileName, stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
