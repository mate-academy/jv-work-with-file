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
    private static final String COMMA = ",";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final int INDEX_TYPE = 0;
    private static final int INDEX_VALUE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String infoFromFile = readFile(fromFileName);
        String report = calculator(infoFromFile);
        writeToFile(report, toFileName);
    }

    private String readFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(LINE_SEPARATOR);
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read form file " + fromFileName, e);
        }
        return builder.toString();
    }

    private String calculator(String data) {
        String[] statistics = data.split(LINE_SEPARATOR);
        int supply = 0;
        int buy = 0;
        for (String statistic : statistics) {
            String[] info = statistic.split(COMMA);
            String operationType = info[INDEX_TYPE];
            int index = Integer.parseInt(info[INDEX_VALUE]);
            if (operationType.equals(SUPPLY)) {
                supply += index;
            } else {
                buy += index;
            }
        }
        return report(supply, buy);
    }

    private String report(int supply, int buy) {
        StringBuilder builder = new StringBuilder();
        builder.append(SUPPLY).append(COMMA).append(supply)
                .append(LINE_SEPARATOR).append(BUY).append(COMMA)
                .append(buy).append(LINE_SEPARATOR).append(RESULT)
                .append(COMMA).append(supply - buy).append(LINE_SEPARATOR);

        return builder.toString();
    }

    private void writeToFile(String text, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(text);
        } catch (IOException e) {
            throw new RuntimeException("Can't write form file " + toFileName, e);
        }
    }
}
