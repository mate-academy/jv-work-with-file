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
                dataFromFile.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading data from file " + fromFileName, e);
        }
        return dataFromFile.toString();
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report.toCharArray());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file", e);
        }
    }

    private String createReport(String dataFromFile) {
        int buyValue = 0;
        int supplyValue = 0;
        for (String pair : dataFromFile.split(LINE_SEPARATOR)) {
            String[] keyValue = pair.split(COMMA);
            if (keyValue[CATEGORY_INDEX].equals(SUPPLY)) {
                supplyValue += Integer.parseInt(keyValue[VALUE_INDEX]);
            } else {
                buyValue += Integer.parseInt(keyValue[VALUE_INDEX]);
            }
        }
        int resultValue = supplyValue - buyValue;
        return SUPPLY + COMMA + supplyValue + System.lineSeparator()
                + BUY + COMMA + buyValue + System.lineSeparator()
                + RESULT + COMMA + resultValue;
    }

    public static void main(String[] args) {
        WorkWithFile marketStats = new WorkWithFile();
        marketStats.getStatistic("apple.csv", "apple_report.txt");
        marketStats.getStatistic("banana.csv", "banana_report.txt");
        marketStats.getStatistic("grape.csv", "grape_report.txt");
        marketStats.getStatistic("orange.csv", "orange_report.txt");
    }
}
