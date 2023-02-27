package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String DELIMITER = "\\W+";
    private static final String DELIMITER_FOR_BUILDER = ",";
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String read = readFromFile(fromFileName);
        String convert = convertToString(read);
        writeToFile(convert, toFileName);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("File can`t be read");
        }
        return builder.toString();
    }

    private String convertToString(String builder) {
        StringBuilder actualResult = new StringBuilder();
        String[] split = builder.split(DELIMITER);
        int buy = 0;
        int supply = 0;
        for (int i = 0; i < split.length; i++) {
            if (split[i].equals(BUY)) {
                buy = buy + Integer.parseInt(split[i + 1]);
            }
            if (split[i].equals(SUPPLY)) {
                supply = supply + Integer.parseInt(split[i + 1]);
            }
        }
        actualResult.append(SUPPLY).append(DELIMITER_FOR_BUILDER).append(supply)
                    .append(System.lineSeparator());
        actualResult.append(BUY).append(DELIMITER_FOR_BUILDER).append(buy)
                    .append(System.lineSeparator());
        actualResult.append(RESULT).append(DELIMITER_FOR_BUILDER).append(supply - buy);
        return actualResult.toString();
    }

    private void writeToFile(String actualResult, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(actualResult);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file", e);
        }
    }
}
