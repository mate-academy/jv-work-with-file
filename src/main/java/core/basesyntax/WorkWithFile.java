package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String OPERATION_SUPPLY = "supply";
    private static final String OPERATION_BUY = "buy";
    private static final String OPERATION_RESULT = "result";
    private static final int INDEX_OPERATION_TYPE = 0;
    private static final int INDEX_AMOUNT = 1;
    private String lineSeparator = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFile(fromFileName);
        String report = createReport(dataFromFile);
        writeReportToFile(report, toFileName);
    }

    private String readFile(String fileName) {
        StringBuilder data = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line).append(lineSeparator);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading file", e);
        }
        return data.toString();
    }

    private String createReport(String dataFromFile) {
        int supplyTotal = 0;
        int buyTotal = 0;
        String[] lines = dataFromFile.split(lineSeparator);
        for (String line : lines) {
            String[] wordsAndValues = line.split(",");
            String operationType = wordsAndValues[INDEX_OPERATION_TYPE];
            int amount = Integer.parseInt(wordsAndValues[INDEX_AMOUNT]);
            if ("supply".equals(operationType)) {
                supplyTotal += amount;
            } else if ("buy".equals(operationType)) {
                buyTotal += amount;
            }
        }
        int result = supplyTotal - buyTotal;
        return OPERATION_SUPPLY + "," + supplyTotal + lineSeparator
                + OPERATION_BUY + "," + buyTotal + lineSeparator
                + OPERATION_RESULT + "," + result;
    }

    private void writeReportToFile(String report, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Error writing report to file", e);
        }
    }
}

