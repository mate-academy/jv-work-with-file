package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";
    private static final int OPERATION_INDEX = 0;
    private static final int VALUE_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        Map<String, Integer> operationTotals = calculateTotals(fromFileName);
        int supply = operationTotals.getOrDefault(SUPPLY, 0);
        int buy = operationTotals.getOrDefault(BUY, 0);
        int result = supply - buy;
        writeToFile(toFileName, supply, buy, result);
        printToConsole(supply, buy, result);
    }

    private Map<String, Integer> calculateTotals(String fileName) {
        Map<String, Integer> totals = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader((fileName))) {
            String line = reader.readLine();
            while (line != null) {
                String[] separatedLine = line.split(COMMA);
                String operation = separatedLine[OPERATION_INDEX];
                int value = Integer.parseInt(separatedLine[VALUE_INDEX]);
                totals.put(operation, totals.getOrDefault(operation, 0) + value);
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fileName, e);
        }
        return totals;
    }

    private void writeToFile(String fileName, int supply, int buy, int result) {
        String report = constructReport(supply, buy, result);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(fileName)))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + fileName, e);
        }
    }

    private String constructReport(int supply, int buy, int result) {
        return SUPPLY + COMMA + supply + "\n" +
               BUY + COMMA + buy + "\n" +
               RESULT + COMMA + result;
    }

    private void printToConsole(int supply, int buy, int result) {
        System.out.println("Supply: " + supply + "\n" + "Buy: " + buy + "\n" + "Result: " + result);
    }
}

