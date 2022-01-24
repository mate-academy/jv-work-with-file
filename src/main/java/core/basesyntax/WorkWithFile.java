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

    public void getStatistic(String fromFileName, String toFileName) {
        writeInfoToReport(createReport(getData(fromFileName)), toFileName);
    }

    private String getData(String fromFile) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFile));
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

    private String createReport(String data) {
        int buy = 0;
        int supply = 0;
        int result = 0;
        String[] info = data.split(System.lineSeparator());
        for (String line : info) {
            String[] lineElements = line.split(",");
            if (lineElements[0].equals(SUPPLY)) {
                supply += Integer.parseInt(lineElements[1]);
            } else if (lineElements[0].equals(BUY)) {
                buy += Integer.parseInt(lineElements[1]);
            }
        }
        result = supply - buy;
        StringBuilder stringBuilderForReport = new StringBuilder();
        stringBuilderForReport.append(SUPPLY).append(",").append(supply)
                .append(System.lineSeparator()).append(BUY).append(",").append(buy)
                .append(System.lineSeparator())
                .append(RESULT).append(",").append(result);
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
