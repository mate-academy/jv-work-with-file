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
    private static final char COMA = ',';

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(fromFileName, toFileName,
                calculateStatistic(readFromFile(fromFileName).split(" ")));
    }

    private String calculateStatistic(String[] splitReadFile) {
        int buy = 0;
        int supply = 0;
        int result = 0;
        for (String stringValue : splitReadFile) {
            int comaIndex = stringValue.indexOf(COMA);
            if (stringValue.substring(0, comaIndex).equals(SUPPLY)) {
                supply += Integer.valueOf(stringValue.substring(comaIndex + 1));
            }
            if (stringValue.substring(0, comaIndex).equals(BUY)) {
                buy += Integer.valueOf(stringValue.substring(comaIndex + 1));
            }
        }
        result = supply - buy;
        StringBuilder builder = new StringBuilder();
        builder.append(SUPPLY).append(COMA).append(supply).append(System.lineSeparator())
                .append(BUY).append(COMA).append(buy).append(System.lineSeparator())
                .append(RESULT).append(COMA).append(result);
        return builder.toString();
    }

    private String readFromFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(" ");
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fromFileName, e);
        }
        return builder.toString();
    }

    private void writeToFile(String fromFileName, String toFileName, String content) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            File file = new File(toFileName);
            file.createNewFile();
            bufferedWriter.write(content);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to File", e);
        }
    }
}
