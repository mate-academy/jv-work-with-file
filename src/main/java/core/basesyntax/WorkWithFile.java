package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION = 0;
    private static final int AMOUNT = 1;
    private static final String SEPARATOR = ",";
    private static final String LINE_SEPARATOR = "@";
    private static final String KEYWORD_ONE = "buy";
    private static final String KEYWORD_TWO = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFile(fromFileName);
        String report = createReport(data);
        writeToFile(report, toFileName);
    }

    public String readFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String readedLine = bufferedReader.readLine();
            while (readedLine != null) {
                stringBuilder.append(readedLine).append(LINE_SEPARATOR);
                readedLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
        return stringBuilder.toString();
    }

    public String createReport(String data) {
        int buy = 0;
        int supply = 0;
        String []line = data.split(LINE_SEPARATOR);
        String [] keyWord;
        for (String s : line) {
            keyWord = s.split(SEPARATOR);
            if (keyWord[OPERATION].equals(KEYWORD_ONE)) {
                buy += Integer.parseInt(keyWord[AMOUNT]);
            }
            if (keyWord[OPERATION].equals(KEYWORD_TWO)) {
                supply += Integer.parseInt(keyWord[AMOUNT]);
            }
        }
        return KEYWORD_TWO + SEPARATOR + supply + System.lineSeparator()
                + KEYWORD_ONE + SEPARATOR + buy + System.lineSeparator()
                + "result," + (supply - buy);
    }

    public void writeToFile(String report, String toFileName) {
        File file = new File(toFileName);
        try (BufferedWriter stringBuffer = new BufferedWriter(new FileWriter(file))) {
            stringBuffer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to " + toFileName, e);
        }
    }
}
