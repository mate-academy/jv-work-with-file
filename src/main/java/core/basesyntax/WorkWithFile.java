package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String DATA_SEPARATOR = ",";
    private static final String TEMPORARY_SEPARATOR = "-";
    private static final String TITLE_SUPPLY = "supply";
    private static final String TITLE_BUY = "buy";
    private static final String TITLE_RESULT = "result";
    private static final int TITLE_INDEX = 0;
    private static final int VALUE_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        for (String data : readFile(fromFileName)) {
            String[] temp = data.split(DATA_SEPARATOR);
            if (temp[TITLE_INDEX].equals(TITLE_SUPPLY)) {
                supply += Integer.valueOf(temp[VALUE_INDEX]);
                continue;
            }
            buy += Integer.valueOf(temp[1]);
        }
        int result = supply - buy;
        writeToFile(createReport(supply, buy, result), toFileName);
    }

    private String[] readFile(String fromFileName) {
        File file = new File(fromFileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            StringBuilder stringBuilder = new StringBuilder();
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(TEMPORARY_SEPARATOR);
                value = bufferedReader.readLine();
            }
            return stringBuilder.toString().split(TEMPORARY_SEPARATOR);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found " + fromFileName, e);
        } catch (IOException e) {
            throw new RuntimeException("File can't be read " + fromFileName, e);
        }
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("File can't be written " + toFileName, e);
        }
    }

    private String createReport(int supply, int buy, int result) {
        StringBuilder builder = new StringBuilder();
        return builder.append(TITLE_SUPPLY)
                .append(DATA_SEPARATOR)
                .append(String.valueOf(supply))
                .append(System.lineSeparator())
                .append(TITLE_BUY)
                .append(DATA_SEPARATOR)
                .append(String.valueOf(buy))
                .append(System.lineSeparator())
                .append(TITLE_RESULT)
                .append(DATA_SEPARATOR)
                .append(String.valueOf(result))
                .toString();
    }
}
