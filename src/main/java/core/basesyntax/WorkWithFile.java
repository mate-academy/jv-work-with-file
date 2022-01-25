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
    private static final int FIRST_ELEM = 0;
    private static final int SECOND_ELEM = 1;
    private static final int THIRD_ELEM = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        writeInfoToReport(createReport(countingStock(getData(fromFileName))), toFileName);
    }

    private String getData(String fromFile) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFile))) {
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file:" + fromFile, e);
        }
        return stringBuilder.toString();
    }

    private String countingStock(String data) {
        int buy = 0;
        int supply = 0;
        int result = 0;
        StringBuilder summaryResult = new StringBuilder();
        String[] info = data.split(System.lineSeparator());
        for (String line : info) {
            String[] lineElements = line.split(COMMA);
            if (lineElements[FIRST_ELEM].equals(SUPPLY)) {
                supply += Integer.parseInt(lineElements[SECOND_ELEM]);
            } else if (lineElements[FIRST_ELEM].equals(BUY)) {
                buy += Integer.parseInt(lineElements[SECOND_ELEM]);
            }
        }
        result = supply - buy;
        return summaryResult.append(supply).append(COMMA)
                .append(buy).append(COMMA).append(result).toString();
    }

    private String createReport(String data) {
        StringBuilder stringBuilderForReport = new StringBuilder();
        String[] dataElements = data.split(COMMA);
        stringBuilderForReport.append(SUPPLY).append(COMMA).append(dataElements[FIRST_ELEM])
                .append(System.lineSeparator()).append(BUY).append(COMMA)
                .append(dataElements[SECOND_ELEM]).append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(dataElements[THIRD_ELEM]);
        return stringBuilderForReport.toString();
    }

    private void writeInfoToReport(String report, String toFile) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile, true))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file:" + toFile, e);
        }
    }
}
