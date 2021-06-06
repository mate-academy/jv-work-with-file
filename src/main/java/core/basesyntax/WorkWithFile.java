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
    private static final String SPACE_SEPARATOR = " ";
    private static final String COMMA_SEPARATOR = ",";
    private static final int WORD_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String resultData = readFromFile(fromFileName);
        String report = createReport(resultData);
        writeToFile(report, toFileName);
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }

    private String createReport(String resultData) {
        int supply = 0;
        int buy = 0;
        int result = 0;
        String[] separatedData = resultData.split(SPACE_SEPARATOR);
        for (String words : separatedData) {
            int increaseAmount = Integer.parseInt(words.split(COMMA_SEPARATOR)[WORD_INDEX]);
            if (words.contains(WORD_SUPPLY)) {
                supply += increaseAmount;
            } else if (words.contains(WORD_BUY)) {
                buy += increaseAmount;
            }
        }
        result = supply - buy;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(WORD_SUPPLY).append(COMMA_SEPARATOR).append(supply)
                .append(System.lineSeparator())
                .append(WORD_BUY).append(COMMA_SEPARATOR).append(buy)
                .append(System.lineSeparator())
                .append(WORD_RESULT).append(COMMA_SEPARATOR).append(result);
        return stringBuilder.toString();
    }

    private String readFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String oneLine = bufferedReader.readLine();
            while (oneLine != null) {
                stringBuilder.append(oneLine).append(SPACE_SEPARATOR);
                oneLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return stringBuilder.toString();
    }
}
