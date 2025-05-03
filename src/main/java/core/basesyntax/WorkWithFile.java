package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class WorkWithFile {
    private static final String SUPPLY_KEY = "supply";
    private static final String BUY_KEY = "buy";
    private static final String RESULT_KEY = "result";
    private static final String LINE_SPLIT_KEY = "\n";
    private static final String CSV_SPLIT_KEY = ",";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int VALUE_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        HashMap<String, Integer> operationTotals = processData(fromFileName);
        String report = generateReport(operationTotals);
        writeToFile(toFileName, report);
    }

    private String read(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append(LINE_SPLIT_KEY);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file! " + fromFileName, e);
        }
        return stringBuilder.toString();
    }

    private HashMap<String, Integer> processData(String fromFileName) {
        HashMap<String, Integer> operationTotals = new HashMap<>();
        String fileContent = read(fromFileName);
        String[] lines = fileContent.split(LINE_SPLIT_KEY);

        for (String line : lines) {
            processLine(line, operationTotals);
        }

        return operationTotals;
    }

    private void processLine(String line, HashMap<String, Integer> operationTotals) {
        String[] values = line.split(CSV_SPLIT_KEY);
        String operationType = values[OPERATION_TYPE_INDEX];
        int tempAmount = Integer.parseInt(values[VALUE_INDEX]);

        operationTotals.merge(operationType, tempAmount, Integer::sum);
    }

    private int calculateResult(int supplySum, int buySum) {
        return supplySum - buySum;
    }

    private String generateReport(HashMap<String, Integer> operationTotals) {
        int supplyTotal = operationTotals.getOrDefault(SUPPLY_KEY, 0);
        int buyTotal = operationTotals.getOrDefault(BUY_KEY, 0);
        int resultTotal = calculateResult(supplyTotal, buyTotal);

        return SUPPLY_KEY + CSV_SPLIT_KEY + supplyTotal + System.lineSeparator()
                + BUY_KEY + CSV_SPLIT_KEY + buyTotal + System.lineSeparator()
                + RESULT_KEY + CSV_SPLIT_KEY + resultTotal;
    }

    private void writeToFile(String toFileName, String report) {
        File resultFile = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(resultFile))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }
}
