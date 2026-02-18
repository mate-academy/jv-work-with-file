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
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        int result = 0;

        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            int value = reader.read();

            while (value != -1) {
                stringBuilder.append((char) value);
                value = reader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }

        String[] lines = stringBuilder.toString().split(System.lineSeparator());
        for (String line : lines) {
            if (line.isEmpty()) {
                continue;
            }
            String[] contentArray = line.split(",");
            if (contentArray.length < 2) {
                continue;
            }
            if (contentArray[OPERATION_TYPE_INDEX].equals(SUPPLY)) {
                supply += Integer.parseInt(contentArray[AMOUNT_INDEX]);
            } else if (contentArray[OPERATION_TYPE_INDEX].equals(BUY)) {
                buy += Integer.parseInt(contentArray[AMOUNT_INDEX]);
            }
        }
        result = supply - buy;

        StringBuilder stringBuilderToFile = new StringBuilder();
        stringBuilderToFile.append(SUPPLY).append(",").append(supply).append(System.lineSeparator())
                .append(BUY).append(",").append(buy).append(System.lineSeparator())
                .append(RESULT).append(",").append(result);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(stringBuilderToFile.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }
}
