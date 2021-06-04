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
    private static final int VALUE_NAME_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            for (String line = bufferedReader.readLine();
                    line != null; line = bufferedReader.readLine()) {
                String[] lineValues = line.split(CSV_SEPARATOR);
                if (lineValues[VALUE_NAME_INDEX].equals(SUPPLY)) {
                    supply += Integer.parseInt(lineValues[AMOUNT_INDEX]);
                } else if (lineValues[VALUE_NAME_INDEX].equals(BUY)) {
                    buy += Integer.parseInt(lineValues[AMOUNT_INDEX]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file");
        }
        StringBuilder builder = new StringBuilder()
                .append(SUPPLY).append(CSV_SEPARATOR).append(supply)
                .append(System.lineSeparator())
                .append(BUY).append(CSV_SEPARATOR).append(buy)
                .append(System.lineSeparator())
                .append(RESULT).append(CSV_SEPARATOR).append(supply - buy);
        writeToFile(toFileName, builder.toString());
    }

    public void writeToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file");
        }
    }
}
