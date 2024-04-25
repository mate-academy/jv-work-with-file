package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

interface StatisticsCalculator {
    String SUPPLY = "supply";
    String BUY = "buy";
    Map<String, Integer> calculateTotals(String fileName);
}

interface StatisticsWriter {
    void writeToFile(String fileName, int supply, int buy, int result);
    void printToConsole(int supply, int buy, int result);
}

class FileStatisticsCalculator implements StatisticsCalculator {
    private static final String COMMA = ",";
    private static final int OPERATION_INDEX = 0;
    private static final int VALUE_INDEX = 1;

    @Override
    public Map<String, Integer> calculateTotals(String fileName) {
        Map<String, Integer> totals = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] separatedLine = line.split(COMMA);
                String operation = separatedLine[OPERATION_INDEX];
                int value = Integer.parseInt(separatedLine[VALUE_INDEX]);
                totals.put(operation, totals.getOrDefault(operation, 0) + value);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fileName, e);
        }
        return totals;
    }
}

class FileStatisticsWriter implements StatisticsWriter {
    private static final String RESULT = "result";
    private static final String COMMA = ",";

    @Override
    public void writeToFile(String fileName, int supply, int buy, int result) {
        String report = constructReport(supply, buy, result);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + fileName, e);
        }
    }

    private String constructReport(int supply, int buy, int result) {
        return StatisticsCalculator.SUPPLY + COMMA + supply + "\n"
             + StatisticsCalculator.BUY + COMMA + buy + "\n"
             + RESULT + COMMA + result;
    }

    @Override
    public void printToConsole(int supply, int buy, int result) {
        System.out.println("Supply: " + supply + "\n" + "Buy: " + buy + "\n" + "Result: " + result);
    }
}

public class WorkWithFile {
    private final StatisticsCalculator calculator;
    private final StatisticsWriter writer;

    public WorkWithFile(StatisticsCalculator calculator, StatisticsWriter writer) {
        this.calculator = calculator;
        this.writer = writer;
    }

    public void getStatistic(String fromFileName, String toFileName) {
        Map<String, Integer> operationTotals = calculator.calculateTotals(fromFileName);
        int supply = operationTotals.getOrDefault(StatisticsCalculator.SUPPLY, 0);
        int buy = operationTotals.getOrDefault(StatisticsCalculator.BUY, 0);
        int result = supply - buy;
        writer.writeToFile(toFileName, supply, buy, result);
        writer.printToConsole(supply, buy, result);
    }
}

   

