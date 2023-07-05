package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String COMMA_SEPARATOR = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String spreadsheet = readFromFile(fromFileName);
        String report = dataProcessing(spreadsheet);
        writeToFile(report, toFileName);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line).append(LINE_SEPARATOR);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("We can't' read from file", e);
        }
        return stringBuilder.toString();
    }

    private String dataProcessing(String spreadsheet) {
        String[] dates = spreadsheet.split(LINE_SEPARATOR);
        int supply = 0;
        int buy = 0;
        for (String data : dates) {
            String[] info = data.split(COMMA_SEPARATOR);
            if (info[0].equals(SUPPLY)) {
                supply += Integer.parseInt(info[1]);
            } else {
                buy += Integer.parseInt(info[1]);
            }
        }
        return createReport(supply, buy);
    }

    private String createReport(int supply, int buy) {
        int result = supply - buy;
        StringBuilder stringBuilder = new StringBuilder(SUPPLY).append(COMMA_SEPARATOR);
        stringBuilder.append(supply).append(LINE_SEPARATOR).append(BUY)
                .append(COMMA_SEPARATOR).append(buy)
                .append(LINE_SEPARATOR).append(RESULT)
                .append(COMMA_SEPARATOR).append(result);
        return stringBuilder.toString();
    }

    private void writeToFile(String createReport, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(createReport);
        } catch (IOException e) {
            throw new RuntimeException("We can't write data to this file" + toFileName + e);
        }
    }
}
