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
    private static final int VALUE_INDEX = 1;
    private static final int CATEGORY_INDEX = 0;
    private static final String COMMA = ",";
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readDataFromFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(toFileName, report);
    }

    private String readDataFromFile(String fromFileName) {
        StringBuilder dataFromFile = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                dataFromFile.append(line).append(LINE_SEPARATOR);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading data from file " + fromFileName, e);
        }
        return dataFromFile.toString();
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file", e);
        }
    }

    private String createReport(String dataFromFile) {
        int buyValue = 0;
        int supplyValue = 0;
        String[] lines = dataFromFile.split(LINE_SEPARATOR);
        for (String line : lines) {
            String[] keyValue = line.split(COMMA);
            if (keyValue.length == 2) {
                if (keyValue[CATEGORY_INDEX].equals(SUPPLY)) {
                    supplyValue += Integer.parseInt(keyValue[VALUE_INDEX]);
                } else if (keyValue[CATEGORY_INDEX].equals(BUY)) {
                    buyValue += Integer.parseInt(keyValue[VALUE_INDEX]);
                }
            } else {
                throw new IllegalArgumentException("Invalid data format: " + line);
            }
        }
        int resultValue = supplyValue - buyValue;
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append(SUPPLY).append(COMMA).append(supplyValue).append(LINE_SEPARATOR)
                .append(BUY).append(COMMA).append(buyValue).append(LINE_SEPARATOR)
                .append(RESULT).append(COMMA).append(resultValue);
        return reportBuilder.toString();
    }

    public static void main(String[] args) {
        WorkWithFile marketStats = new WorkWithFile();
        marketStats.getStatistic("apple.csv", "apple_report.txt");
        marketStats.getStatistic("banana.csv", "banana_report.txt");
        marketStats.getStatistic("grape.csv", "grape_report.txt");
        marketStats.getStatistic("orange.csv", "orange_report.txt");
    }
}
