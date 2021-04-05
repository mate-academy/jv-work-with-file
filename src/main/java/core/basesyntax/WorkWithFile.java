package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String WORD_SUPPLY = "supply";
    private static final String WORD_BUY = "buy";
    private static final String WORD_RESULT = "result";
    private static final String SEPARATOR_COMMA = ",";
    private static final String SEPARATOR_SPACE = " ";

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFromFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(report, toFileName);

    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("We can't add new data to file" + toFileName, e);
        }
    }

    private String createReport(String dataFromFile) {
        int supply = 0;
        int buy = 0;
        String[] dataInWords = dataFromFile.split(SEPARATOR_SPACE);
        for (String word : dataInWords) {
            int amountToAdd = Integer.parseInt(word.substring(word.indexOf(SEPARATOR_COMMA) + 1));
            if (word.contains(WORD_SUPPLY)) {
                supply += amountToAdd;
            } else if (word.contains(WORD_BUY)) {
                buy += amountToAdd;
            }
        }
        int result = supply - buy;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(WORD_SUPPLY).append(SEPARATOR_COMMA).append(supply)
                .append(System.lineSeparator()).append(WORD_BUY).append(SEPARATOR_COMMA)
                .append(buy).append(System.lineSeparator()).append(WORD_RESULT)
                .append(SEPARATOR_COMMA).append(result);
        return stringBuilder.toString();
    }

    private String readFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(SEPARATOR_SPACE);
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("We can't read from file " + fromFileName, e);
        }
        return stringBuilder.toString();
    }
}
