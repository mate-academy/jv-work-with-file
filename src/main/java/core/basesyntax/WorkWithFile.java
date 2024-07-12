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
    private static final int NUMBER_ZERO = 0;
    private static final int NUMBER_ONE = 1;
    private static final int NUMBER_TWO = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFromFile(fromFileName);
        String report = calculateReport(data);
        writeToFile(toFileName, report);
    }

    private String readFromFile(String fileName) {
        StringBuilder data = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                data.append(line).append(System.lineSeparator());
            }
            return data.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read info from file", e);
        }
    }

    private String calculateReport(String data) {
        int totalSupply = NUMBER_ZERO;
        int totalBuy = NUMBER_ZERO;
        String[] lines = data.split(System.lineSeparator());
        for (String line : lines) {
            String[] parts = line.split(COMMA);
            if (parts[NUMBER_ZERO].equals(SUPPLY)) {
                totalSupply += Integer.parseInt(parts[NUMBER_ONE]);
            } else if (parts[NUMBER_ZERO].equals(BUY)) {
                totalBuy += Integer.parseInt(parts[NUMBER_ONE]);
            }
        }
        return buildReportContent(totalSupply, totalBuy);
    }

    private String buildReportContent(int totalSupply, int totalBuy) {
        StringBuilder text = new StringBuilder();
        text.append(SUPPLY).append(COMMA).append(totalSupply)
                .append(System.lineSeparator())
                .append(BUY).append(COMMA).append(totalBuy)
                .append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(totalSupply - totalBuy);
        return text.toString();
    }

    private void writeToFile(String reportName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(reportName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write info in file", e);
        }
    }
}
