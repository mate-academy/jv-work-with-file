package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String COMMA_SEPARATOR = ",";
    public static final int START_INDEX = 0;
    public static final int SECOND_INDEX = 2;
    public static final String WORD_SUPPLY = "supply";
    public static final String WORD_BUY = "buy";
    public static final String WORD_RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataToRead = getDataFromFile(fromFileName);
        String dataToWrite = logicForResult(dataToRead);
        writeToFile(toFileName, dataToWrite);
    }

    private String[] getDataFromFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(COMMA_SEPARATOR);
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file: " + fileName, e);
        }
        return stringBuilder.toString().split(COMMA_SEPARATOR);
    }

    private String logicForResult(String[] data) {
        int supply = START_INDEX;
        int buy = START_INDEX;
        for (int i = START_INDEX; i < data.length; i += SECOND_INDEX) {
            if (data[i].equals(WORD_SUPPLY)) {
                supply += Integer.parseInt(data[i + 1]);
            } else {
                buy += Integer.parseInt(data[i + 1]);
            }
        }
        return WORD_SUPPLY + COMMA_SEPARATOR + supply + System.lineSeparator()
                + WORD_BUY + COMMA_SEPARATOR + buy + System.lineSeparator()
                + WORD_RESULT + COMMA_SEPARATOR + (supply - buy);
    }

    private void writeToFile(String fileName, String data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file: " + fileName, e);
        }
    }
}
