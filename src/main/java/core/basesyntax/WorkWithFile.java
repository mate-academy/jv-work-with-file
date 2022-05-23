package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WorkWithFile {
    private static final String BUY_PARAMETER = "buy";
    private static final String SUPPLY_PARAMETER = "supply";
    private static final String WORD_RESULT = "result";
    private static final String COMMA_JOINER = ",";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final int PARAMETER_INDEX = 0;
    private static final int VALUE_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String rawData = readFile(fromFileName);
        String evaluatedData = evaluateData(rawData);
        writeFile(toFileName, evaluatedData);
    }

    private String readFile(String fileName) {
        try (BufferedReader bufferedReader =
                     Files.newBufferedReader(Paths.get(fileName))) {
            String value = bufferedReader.readLine();
            final StringBuilder stringBuilder = new StringBuilder();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't correctly read data from file " + fileName, e);
        }
    }

    private String evaluateData(String data) {
        int supply = 0;
        int buy = 0;
        StringBuilder stringBuilder = new StringBuilder();
        String[] array = data.split(LINE_SEPARATOR);
        for (String line : array) {
            String[] words = line.split(COMMA_JOINER);
            if (words[PARAMETER_INDEX].equals(BUY_PARAMETER)) {
                buy += Integer.parseInt(words[1]);
            } else if (words[PARAMETER_INDEX].equals(SUPPLY_PARAMETER)) {
                supply += Integer.parseInt(words[VALUE_INDEX]);
            }
        }
        stringBuilder.append(SUPPLY_PARAMETER).append(COMMA_JOINER)
                .append(supply).append(LINE_SEPARATOR)
                .append(BUY_PARAMETER).append(COMMA_JOINER).append(buy).append(LINE_SEPARATOR)
                .append(WORD_RESULT).append(COMMA_JOINER).append(supply - buy);
        return stringBuilder.toString();
    }

    private void writeFile(String fileName, String data) {
        try (BufferedWriter bufferedWriter =
                     Files.newBufferedWriter(Paths.get(fileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't correctly write data to file " + fileName, e);
        }
    }
}
