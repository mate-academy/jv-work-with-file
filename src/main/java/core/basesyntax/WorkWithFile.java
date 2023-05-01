package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";
    private static final int NAME_INDEX = 0;
    private static final int NUMBER_INDEX = 1;
    private static final String SPACE = " ";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromFile = readFile(fromFileName);
        String report = generateReport(dataFromFile);
        writeToFile(toFileName, report);
    }

    private String [] readFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(SPACE);
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read to file", e);
        }
        return builder.toString().split(SPACE);
    }

    private String generateReport(String[] fromFileDate) {
        int buyCounter = 0;
        int supplyCounter = 0;
        for (String line : fromFileDate) {
            String[] words = line.split(COMMA);
            if (words[NAME_INDEX].equals(BUY)) {
                buyCounter += Integer.parseInt(words[NUMBER_INDEX]);
            }
            if (words[NAME_INDEX].equals(SUPPLY)) {
                supplyCounter += Integer.parseInt(words[NUMBER_INDEX]);
            }
        }
        StringBuilder report = new StringBuilder();
        report.append(SUPPLY).append(COMMA).append(supplyCounter).append(System.lineSeparator())
                .append(BUY).append(COMMA).append(buyCounter).append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(supplyCounter - buyCounter)
                .append(System.lineSeparator());
        return report.toString();
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can`t writer to file", e);
        }
    }
}
